package com.fantasy.football.englishpremierleague.utils;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.util.IOUtils;
import com.fantasy.football.englishpremierleague.factories.clients.model.LeagueSummary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Arrays;

@Service
public class AwsImageDownloader implements ImageDownloader {

    @Autowired
    private AmazonS3 amazonS3Client;

    @Override
    public void downloadAndSaveImages(LeagueSummary leagueSummary) {
        Arrays.stream(leagueSummary.elements).forEach(player -> {
            try {
                String path =
                        String.format(
                                "https://platform-static-files.s3.amazonaws.com/premierleague/photos/players/110x140/p%d.png",
                                player.code);

                URL url = new URL(path);
                try (InputStream input = url.openStream()) {
                    byte[] content = IOUtils.toByteArray(input);

                    ObjectMetadata objectMetadata = new ObjectMetadata();
                    objectMetadata.setContentLength(content.length);
                    objectMetadata.setContentType("image/png");

                    PutObjectRequest putObjectRequest =
                            new PutObjectRequest(
                                    "segoo-inc-fantasy-football",
                                    String.format("images/%d", player.code),
                                    new ByteArrayInputStream(content),
                                    objectMetadata);
                    putObjectRequest.setCannedAcl(CannedAccessControlList.PublicRead);
                    amazonS3Client.putObject(putObjectRequest);
                    System.out.printf("Finished uploading image for %s %s\n", player.firstName, player.secondName);
                } catch (IOException io) {
                    System.err.println(io.getMessage());
                }
            } catch (Exception e) {
                System.err.println(e.getMessage());
            }
        });
    }
}
