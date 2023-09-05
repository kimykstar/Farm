package com.Hanium.Farm.Farm.Controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

@RestController
public class ARController {

    // 본 서버에서는 gltf파일의 경로는 ~/Farm/build/libs/models안에 있어야 함
    // 아래 코드는 본 서버 기준으로 작성된 코드임
    @GetMapping(value = "arimage")
    public String pushARImage(@RequestParam("fruit_name") String fruit_name) throws IOException {
        String gltfPath = "models/" + fruit_name + ".gltf";

        String gltfJson = new String(Files.readAllBytes(Paths.get(gltfPath)), StandardCharsets.UTF_8);
        return gltfJson;
    }

}
