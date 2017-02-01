package com.dbdbdeep.modoostar.model;

import com.dbdbdeep.modoostar.helper.BaseModel;

import java.util.List;

/**
 * Created by leekh on 2017. 1. 11..
 */

public class CompetitionHumanIns extends BaseModel {
    public int ch_seq;
    public int count;
    public List<ModelInfo> list;

    @Override
    public String toString() {
        return "CompetitionHumanIns{" +
                "ch_seq=" + ch_seq +
                ", count=" + count +
                ", list=" + list +
                '}' + super.toString();
    }
}