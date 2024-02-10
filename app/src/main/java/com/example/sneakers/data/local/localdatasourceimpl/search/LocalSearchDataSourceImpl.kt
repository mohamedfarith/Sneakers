package com.example.sneakers.data.local.localdatasourceimpl.search

import com.example.sneakers.constants.AppConstants
import com.example.sneakers.data.models.home.SneakerListDto
import com.example.sneakers.domain.local.localdatasource.search.LocalSearchDataSource
import com.example.sneakers.network.NetworkState
import com.google.gson.Gson
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class LocalSearchDataSourceImpl @Inject constructor() : LocalSearchDataSource {
    override suspend fun getSneakersList(): Flow<NetworkState> {
        return flow<NetworkState> {
            emit(NetworkState.Success(Gson().fromJson(fakeHomeData(), SneakerListDto::class.java)))
        }.catch {
            emit(NetworkState.Failure(message = AppConstants.DEFAULT_API_FAILURE_MESSAGE))
        }
    }
}

private fun fakeHomeData(): String {
    return "{\n" +
            "  \"list\": [\n" +
            "    {\n" +
            "      \"id\": \"3fa85f64-5717-4562-b3fc-2c963f66afa1\",\n" +
            "      \"brand\": \"Adidas\",\n" +
            "      \"gender\": \"Male\",\n" +
            "      \"media\": [\n" +
            "        \"https://i.imgur.com/qNOjJje.jpeg\",\n" +
            "        \"https://i.imgur.com/NjfCFnu.jpeg\",\n" +
            "        \"https://i.imgur.com/eYtvXS1.jpeg\"\n" +
            "      ],\n" +
            "      \"releaseDate\": \"2022-12-15\",\n" +
            "      \"retailPrice\": 39,\n" +
            "      \"styleId\": \"string\",\n" +
            "      \"shoe\": \"string\",\n" +
            "      \"title\": \"Futuristic Holographic Soccer Cleats\",\n" +
            "      \"description\": \"Step onto the field and stand out from the crowd with these eye-catching holographic soccer cleats. Designed for the modern player, these cleats feature a sleek silhouette, lightweight construction for maximum agility, and durable studs for optimal traction. The shimmering holographic finish reflects a rainbow of colors as you move, ensuring that you'll be noticed for both your skills and style. Perfect for the fashion-forward athlete who wants to make a statement.\",\n" +
            "      \"year\": 2023\n" +
            "    },\n" +
            "    {\n" +
            "      \"id\": \"3fa85f64-5717-4562-b3fc-2c963f66afa2\",\n" +
            "      \"brand\": \"Adidas\",\n" +
            "      \"gender\": \"Male\",\n" +
            "      \"media\": [\n" +
            "        \"https://i.imgur.com/62gGzeF.jpeg\",\n" +
            "        \"https://i.imgur.com/5MoPuFM.jpeg\",\n" +
            "        \"https://i.imgur.com/sUVj7pK.jpeg\"\n" +
            "      ],\n" +
            "      \"releaseDate\": \"2022-12-15\",\n" +
            "      \"retailPrice\": 39,\n" +
            "      \"styleId\": \"string\",\n" +
            "      \"shoe\": \"string\",\n" +
            "      \"title\": \"Rainbow Glitter High Heels\",\n" +
            "      \"description\": \"Step into the spotlight with these eye-catching rainbow glitter high heels. Designed to dazzle, each shoe boasts a kaleidoscope of shimmering colors that catch and reflect light with every step. Perfect for special occasions or a night out, these stunners are sure to turn heads and elevate any ensemble.\",\n" +
            "      \"year\": 2023\n" +
            "    },\n" +
            "    {\n" +
            "      \"id\": \"3fa85f64-5717-4562-b3fc-2c963f66afa3\",\n" +
            "      \"brand\": \"Sketchers\",\n" +
            "      \"gender\": \"Female\",\n" +
            "      \"media\": [\n" +
            "        \"https://i.imgur.com/9qrmE1b.jpeg\",\n" +
            "        \"https://i.imgur.com/wqKxBVH.jpeg\",\n" +
            "        \"https://i.imgur.com/sWSV6DK.jpeg\"\n" +
            "      ],\n" +
            "      \"releaseDate\": \"2022-12-15\",\n" +
            "      \"retailPrice\": 39,\n" +
            "      \"styleId\": \"string\",\n" +
            "      \"shoe\": \"string\",\n" +
            "      \"title\": \"Chic Summer Denim Espadrille Sandals\",\n" +
            "      \"description\": \"Step into summer with style in our denim espadrille sandals. Featuring a braided jute sole for a classic touch and adjustable denim straps for a snug fit, these sandals offer both comfort and a fashionable edge. The easy slip-on design ensures convenience for beach days or casual outings.\",\n" +
            "      \"year\": 2023\n" +
            "    },\n" +
            "    {\n" +
            "      \"id\": \"3fa85f64-5717-4562-b3fc-2c963f66afa4\",\n" +
            "      \"brand\": \"Sketchers\",\n" +
            "      \"gender\": \"Male\",\n" +
            "      \"media\": [\n" +
            "        \"https://i.imgur.com/hKcMNJs.jpeg\",\n" +
            "        \"https://i.imgur.com/NYToymX.jpeg\",\n" +
            "        \"https://i.imgur.com/HiiapCt.jpeg\"\n" +
            "      ],\n" +
            "      \"releaseDate\": \"2022-12-15\",\n" +
            "      \"retailPrice\": 39,\n" +
            "      \"styleId\": \"string\",\n" +
            "      \"shoe\": \"string\",\n" +
            "      \"title\": \"Vibrant Runners: Bold Orange & Blue Sneakers\",\n" +
            "      \"description\": \"Step into style with these eye-catching sneakers featuring a striking combination of orange and blue hues. Designed for both comfort and fashion, these shoes come with flexible soles and cushioned insoles, perfect for active individuals who don't compromise on style. The reflective silver accents add a touch of modernity, making them a standout accessory for your workout or casual wear.\",\n" +
            "      \"year\": 2023\n" +
            "    },\n" +
            "    {\n" +
            "      \"id\": \"3fa85f64-5717-4562-b3fc-2c963f66afa5\",\n" +
            "      \"brand\": \"Sketchers\",\n" +
            "      \"gender\": \"Female\",\n" +
            "      \"media\": [\n" +
            "        \"https://i.imgur.com/mcW42Gi.jpeg\",\n" +
            "        \"https://i.imgur.com/mhn7qsF.jpeg\",\n" +
            "        \"https://i.imgur.com/F8vhnFJ.jpeg\"\n" +
            "      ],\n" +
            "      \"releaseDate\": \"2022-12-15\",\n" +
            "      \"retailPrice\": 39,\n" +
            "      \"styleId\": \"string\",\n" +
            "      \"shoe\": \"string\",\n" +
            "      \"title\": \"Vibrant Pink Classic Sneakers\",\n" +
            "      \"description\": \"Step into style with our Vibrant Pink Classic Sneakers! These eye-catching shoes feature a bold pink hue with iconic white detailing, offering a sleek, timeless design. Constructed with durable materials and a comfortable fit, they are perfect for those seeking a pop of color in their everyday footwear. Grab a pair today and add some vibrancy to your step!\",\n" +
            "      \"year\": 2023\n" +
            "    },\n" +
            "    {\n" +
            "      \"id\": \"3fa85f64-5717-4562-b3fc-2c963f66afa6\",\n" +
            "      \"brand\": \"Gucci\",\n" +
            "      \"gender\": \"Female\",\n" +
            "      \"media\": [\n" +
            "             \"https://i.imgur.com/HqYqLnW.jpeg\",\n" +
            "        \"https://i.imgur.com/RlDGnZw.jpeg\",\n" +
            "        \"https://i.imgur.com/qa0O6fg.jpeg\"\n" +
            "      ],\n" +
            "      \"releaseDate\": \"2022-12-15\",\n" +
            "      \"retailPrice\": 39,\n" +
            "      \"styleId\": \"string\",\n" +
            "      \"shoe\": \"string\",\n" +
            "      \"title\": \"Futuristic Chic High-Heel Boots\",\n" +
            "      \"description\": \"Elevate your style with our cutting-edge high-heel boots that blend bold design with avant-garde aesthetics. These boots feature a unique color-block heel, a sleek silhouette, and a versatile light grey finish that pairs easily with any cutting-edge outfit. Crafted for the fashion-forward individual, these boots are sure to make a statement.\",\n" +
            "      \"year\": 2023\n" +
            "    },\n" +
            "     {\n" +
            "      \"id\": \"3fa85f64-5717-4562-b3fc-2c963f66afa7\",\n" +
            "      \"brand\": \"Sketchers\",\n" +
            "      \"gender\": \"Male\",\n" +
            "      \"media\": [\n" +
            "        \"https://i.imgur.com/npLfCGq.jpeg\",\n" +
            "        \"https://i.imgur.com/vYim3gj.jpeg\",\n" +
            "        \"https://i.imgur.com/HxuHwBO.jpeg\"\n" +
            "      ],\n" +
            "      \"releaseDate\": \"2022-12-15\",\n" +
            "      \"retailPrice\": 39,\n" +
            "      \"styleId\": \"string\",\n" +
            "      \"shoe\": \"string\",\n" +
            "      \"title\": \"Futuristic Silver and Gold High-Top Sneaker\",\n" +
            "      \"description\": \"Step into the future with this eye-catching high-top sneaker, designed for those who dare to stand out. The sneaker features a sleek silver body with striking gold accents, offering a modern twist on classic footwear. Its high-top design provides support and style, making it the perfect addition to any avant-garde fashion collection. Grab a pair today and elevate your shoe game!\",\n" +
            "      \"year\": 2023\n" +
            "    }\n" +
            "  ]\n" +
            "}"
}
