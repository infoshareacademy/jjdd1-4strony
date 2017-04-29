package com.isacademy.jjdd1.czterystrony;

import javax.ejb.EJB;
import javax.ejb.Schedule;
import javax.ejb.Singleton;
import java.io.IOException;

@Singleton
public class TimerService {

    @EJB
    DataDownloader dataDownloader;

    @Schedule(second = "*/30", minute = "*", hour = "*", persistent=false)
    void downloadData() {
        try {
            dataDownloader.download();
        } catch (IOException e) {
            System.out.println("CANNOT DOWNLOAD FUNDS RATINGS DATA.");
        }
    }
}
