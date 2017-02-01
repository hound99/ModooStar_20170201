package com.dbdbdeep.modoostar.model;

import com.dbdbdeep.modoostar.helper.BaseModel;

public class ModelInfo extends BaseModel {
    public int index;
    public int mp_seq;
    public String mpi_link;
    public String m_id;
    public String ma_code;
    public String m_nickname;
    public String mp_sex;
    public String mp_birth;
    public String mp_locate;
    public String mp_cover;
    public int cnt_win;
    public int mp_cp_champ;
    public int mp_cp_win;
    public int mp_cp_lose;

    @Override
    public String toString() {
        return "ModelInfo{" +
                "index=" + index +
                ", mp_seq=" + mp_seq +
                ", mpi_link='" + mpi_link + '\'' +
                ", m_id='" + m_id + '\'' +
                ", ma_code='" + ma_code + '\'' +
                ", m_nickname='" + m_nickname + '\'' +
                ", mp_sex='" + mp_sex + '\'' +
                ", mp_birth='" + mp_birth + '\'' +
                ", mp_locate='" + mp_locate + '\'' +
                ", mp_cover='" + mp_cover + '\'' +
                ", cnt_win=" + cnt_win +
                ", mp_cp_champ=" + mp_cp_champ +
                ", mp_cp_win=" + mp_cp_win +
                ", mp_cp_lose=" + mp_cp_lose +
                "} " + super.toString();
    }
}