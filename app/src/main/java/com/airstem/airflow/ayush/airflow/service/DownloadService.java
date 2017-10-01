package com.airstem.airflow.ayush.airflow.service;

import android.content.Context;

import com.airstem.airflow.ayush.airflow.model.collection.CollectionDownload;
import com.tonyodev.fetch.Fetch;
import com.tonyodev.fetch.request.Request;

/**
 * Created by mcd-50 on 30/9/17.
 */

public class DownloadService {

    public Fetch getFetchInstance(Context context){
        return Fetch.newInstance(context);
    }

    public CollectionDownload enqueueRequest(Fetch fetch, String downloadUrl, String savePath, String fileName){
        Request request = new Request(downloadUrl, savePath, fileName);
        CollectionDownload collectionDownload = new CollectionDownload();
        collectionDownload.setDownloadId(fetch.enqueue(request));
        return collectionDownload;
    }

}
