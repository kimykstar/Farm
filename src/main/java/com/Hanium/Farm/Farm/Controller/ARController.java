package com.Hanium.Farm.Farm.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

@RestController
public class ARController {

    @GetMapping(value = "arimage")
    public String pushARImage(@RequestParam("fruit_name") String fruit_name) throws IOException {
        String gltfPath = "models/" + fruit_name + "_side.gltf";

        String gltfJson = new String(Files.readAllBytes(Paths.get(gltfPath)), StandardCharsets.UTF_8);
        return gltfJson;
    }

    @GetMapping(value = "arwholeimage")
    public String putARwholeimage(@RequestParam("fruit_name") String fruit_name) throws IOException{
        String gltfPath = "models/" + fruit_name + ".gltf";

        String gltfJson = new String(Files.readAllBytes(Paths.get(gltfPath)), StandardCharsets.UTF_8);
        return gltfJson;
    }

}
