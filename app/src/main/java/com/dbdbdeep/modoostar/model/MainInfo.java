package com.dbdbdeep.modoostar.model;

import com.dbdbdeep.modoostar.helper.BaseModel;

import java.util.List;

/**
 * Created by leekh on 2017. 1. 11..
 */

public class MainInfo extends BaseModel {
    public int count;
    public int count_cp_s;
    public int count_cp_e;
    public int count_cp_g;
    public List<ModelInfo> list;
    public List<CompetitionTheme> list_cp_s;
    public List<CompetitionTheme> list_cp_e;
    public List<CompetitionTheme> list_cp_g;

    @Override
    public String toString() {
        return "MainInfo{" +
                "count=" + count +
                ", count_cp_s=" + count_cp_s +
                ", count_cp_e=" + count_cp_e +
                ", count_cp_g=" + count_cp_g +
                ", list=" + list +
                ", list_cp_s=" + list_cp_s +
                ", list_cp_e=" + list_cp_e +
                ", list_cp_g=" + list_cp_g +
                '}' + super.toString();
    }
}