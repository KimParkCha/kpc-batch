package com.ssafy.kpc.controller;

import com.ssafy.kpc.retrofit.NaverAPIResponse;
import com.ssafy.kpc.retrofit.NaverAPIService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import retrofit2.Call;
import retrofit2.Response;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("v1/api")
public class RealEstateController {

    @Autowired
    NaverAPIService naverAPIService;

    @GetMapping("/articles")
    public List<NaverAPIResponse> articles() {
        Call<List<NaverAPIResponse>> call = naverAPIService.fetchRealEstate(
                1162010200L,
                17,
                "RETAIL",
                "",
                "",
                "",
                "",
                "",
                "APT%3APRE",

                "",
//                0L,
//                900000000L,
//                0L,
//                900000000L,
//                0L,
//                900000000L,
                "",
                "",
                "",
                "",
                false,
                false,
                "",
                "",
                "",
                126.9066669,
                126.9246913,
                37.4843738,
                37.4779884,
                true
                );
        try {
            Response<List<NaverAPIResponse>> response = call.execute();
            if (response.isSuccessful()) {
                return response.body();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return null;
    }
}
