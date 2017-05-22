package com.isacademy.jjdd1.czterystrony.updaters;

import com.isacademy.jjdd1.czterystrony.factories.OfeFactory;
import com.isacademy.jjdd1.czterystrony.model.InvestFund;
import com.isacademy.jjdd1.czterystrony.model.Ofe;
import com.isacademy.jjdd1.czterystrony.repositories.OfeRepository;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.Objects;

import static com.isacademy.jjdd1.czterystrony.utilities.Constants.OFE_LIST_SOURCE;
import static com.isacademy.jjdd1.czterystrony.utilities.Constants.RATINGS_DATA_FILE_EXTENSION;

@Stateless
public class OfesUpdater {

    @Inject
    OfeRepository ofeRepository;

    public void update() throws IOException {
        URL url = new URL(OFE_LIST_SOURCE);
        URLConnection urlConnection = url.openConnection();
        InputStream stream = urlConnection.getInputStream();
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(stream));
        bufferedReader.lines()
                .filter(record -> containsDataFileExtension(record))
                .map(OfeFactory::create)
                .filter(ofe -> isNew(ofe) || isUpdated(ofe))
                .forEach(ofe -> addNewOrUpdateExisting(ofe));
    }

    private Boolean containsDataFileExtension(String record) {
        return record.matches("(.*)" + RATINGS_DATA_FILE_EXTENSION + "(.*)");
    }

    private boolean isNew(Ofe ofe) {
        return Objects.isNull(ofeRepository.getById(ofe.getId()));
    }

    private boolean isUpdated(Ofe ofe) {
        Ofe retrievedOfe = ofeRepository.getById(ofe.getId());
        return ofe.getLastRatingDate().isAfter(retrievedOfe.getLastRatingDate());
    }

    private void addNewOrUpdateExisting(Ofe ofe) {
        if (isNew(ofe)) {
            ofeRepository.add(ofe);
        } else {
            Ofe updatedOfe = ofeRepository.getById(ofe.getId());
            updatedOfe.setName(ofe.getName());
            updatedOfe.setLastRatingDate(ofe.getLastRatingDate());
        }
    }
}
