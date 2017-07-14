package com.airstem.airflow.ayush.airflow.predicates;

import com.google.api.client.repackaged.com.google.common.base.Predicate;

import javax.annotation.Nullable;

/**
 * Created by mcd-50 on 14/7/17.
 */

public class Filter<T> implements Predicate<T> {
    private T varc1;
    public boolean test(T varc){
        if(varc1.equals(varc)){
            return true;
        }
        return false;
    }

    @Override
    public boolean apply(@Nullable T t) {
        return false;
    }
}

