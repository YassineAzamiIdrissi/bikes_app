import {Component, OnInit} from '@angular/core';
import {TokenService} from "../../../../services/tokenService/token.service";
import {Router} from "@angular/router";
import SockJS from 'sockjs-client';
import * as Stomp from 'stompjs';
import {Notification} from "./notification";
import {ToastrService} from "ngx-toastr";

@Component({
  selector: 'app-menu',
  templateUrl: './menu.component.html',
  styleUrl: './menu.component.scss'
})
export class MenuComponent implements OnInit {
  username: string = "";
  socketClient: any = null;
  notificationSubscription: any = null;

  notifications: Array<Notification> = [];
  notifDisplayed = false;
  count = 0;

  constructor(private tokenService: TokenService,
              private router: Router,
              private toasterService: ToastrService) {
  }

  ngOnInit() {
    const payload = this.tokenService.decodePayload();
    this.username = payload.fullname;
    // we will subscribe to the pipe :
    // /user/103984839-aznxsskia-1920 comes from the webSocketConfig and the NotificationService.. check it up !
    if (this.tokenService.token) {
      const parsedData = this.tokenService.decodePayload();
      let ws = new SockJS("http://localhost:8088/api/v1/ws");
      this.socketClient = Stomp.over(ws);
      this.socketClient.connect
      (
        {Authorization: `Bearer ${this.tokenService.token}`},
        () => {
          this.notificationSubscription = this.socketClient.subscribe(
            `/user/${parsedData.id}/notifications`, (message: any) => {
              console.log("This is the notification : ");
              const notification: Notification = JSON.parse(message.body);
              if (notification) {
                this.notifications.unshift(notification);
                switch (notification.status) {
                  case 'BORROWED':
                    this.toasterService.info(notification.message, "A new borrowing ");
                    break;
                  case 'RETURNED':
                    this.toasterService.warning(notification.message, "A new return");
                    break;
                  case 'RETURN_APPROVED':
                    this.toasterService.success(notification.message, "Return approved");
                    break;
                }
                this.count++;
              }
            }
          );
        }
      );
    }
  }

  logout() {
    localStorage.removeItem("token");
    this.router.navigate(["login"]);
  }

}
