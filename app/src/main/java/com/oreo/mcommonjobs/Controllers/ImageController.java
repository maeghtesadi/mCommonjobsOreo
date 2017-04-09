package com.oreo.mcommonjobs.Controllers;

import com.oreo.mcommonjobs.R;

/**
 * Created by jason on 2017-04-09.
 */

public class ImageController {


 public int setJobImage(String typeofjob){

    switch (typeofjob){
        case "Painting":
            return R.drawable.painting_duties;
        case "Gardening":
            return R.drawable.gardening_duties;
        case "Vehicle Repair":
            return R.drawable.vehicle_repairs;
        case "Restaurant":
            return R.drawable.restaurant_duties;
        case "House Work":
            return R.drawable.house_repairs;
        case "Care":
            return R.drawable.care_duties;
        default:
            return R.drawable.roundedbtn;



    }


 }


}
