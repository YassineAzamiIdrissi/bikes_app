import {Component, OnInit} from '@angular/core';
import {BikeService} from "../../../../services/services/bike.service";
import {ActivatedRoute} from "@angular/router";
import {BikeRequest} from "../../../../services/models/bike-request";
import {Router} from "@angular/router";

@Component({
  selector: 'app-bike-form',
  templateUrl: './bike-form.component.html',
  styleUrl: './bike-form.component.scss'
})
export class BikeFormComponent implements OnInit {

  selectedPic: string | undefined = "";
  selectedFile: any;
  bikeRequest: BikeRequest = {
    bikeName: "",
    description: "",
    shareable: false,
    techIdentifier: "",
  };
  bikeId: number = 0;
  validationErrors = [];
  debugMessage = "";
  staticBikeName: string = "";

  constructor(private bikeService: BikeService,
              private router: Router,
              private activateRouter: ActivatedRoute) {
  }

  ngOnInit() {
    this.bikeId = this.activateRouter.snapshot.params["bikeId"];
    console.log(this.bikeId);
    if (this.bikeId) {
      this.fetchConcernedBike();
    }
    console.log("Selected picture");
    console.log(this.selectedPic);
  }

  fetchConcernedBike() {
    this.bikeService.getBikeById(
      {
        'bike-id': this.bikeId as number
      }
    ).subscribe({
      next: (resp) => {
        this.bikeRequest.id = resp.id as number;
        this.bikeRequest.bikeName = resp.bikeName as string;
        this.staticBikeName = resp.bikeName as string;
        this.bikeRequest.description = resp.description as string;
        this.bikeRequest.shareable = resp.shareable as boolean;
        this.bikeRequest.techIdentifier = resp.techIdentifier as string;
        this.selectedPic = "data:image/jpg;base64, " + resp.picture;
      },
      error: (err) => {
        console.log("Fetching the concerned bike");
        console.log(err);
      }
    });
  }

  setFile(form: any) {
    this.selectedFile = form.target.files[0];
    if (this.selectedFile) {
      const reader = new FileReader();
      reader.onload = () => {
        this.selectedPic = reader.result as string;
      }
      reader.readAsDataURL(this.selectedFile);
    }
  }

  saveBike() {
    this.validationErrors = [];
    this.bikeService.saveNewBike({
      body: this.bikeRequest
    }).subscribe({
      next: (bikeId) => {
        if (this.bikeId) {
          this.bikeRequest.id = this.bikeId;
        }
        if (this.selectedFile) {
          this.bikeService.setBikePicture(
            {
              'bike-id': bikeId,
              body: {
                'file': this.selectedFile
              }
            }
          ).subscribe({
            next: () => {
              this.router.navigate(['/bikes/mine']);
              if (this.bikeId) {
                this.router.navigate(['/bikes/mine']);
              }
            },
            error: (err) => {
              this.debugMessage = "Something went wrong setBikePicture";
              console.log(err);
            }
          })
        } else {
          this.router.navigate(["/bikes/mine"]);
        }
      },
      error: (error) => {
        console.log(error);
        this.validationErrors = error.error.errors;
      }
    })
  }

  resetData() {
    this.bikeRequest = {
      bikeName: "",
      description: "",
      shareable: false,
      techIdentifier: "",
    }
  }
}
