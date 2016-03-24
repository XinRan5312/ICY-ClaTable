package com.example.zane.icy_clatable.event;

import com.example.zane.icy_clatable.data.bean.Clazz;

import java.util.List;

/**
 * Created by Zane on 16/3/24.
 */
public class ClazzDetailEvent {

    private List<Clazz.ClassEntity.MutilpleEntity> clazzes;

    public ClazzDetailEvent(List<Clazz.ClassEntity.MutilpleEntity> clazzes){
        this.clazzes = clazzes;
    }

    public List<Clazz.ClassEntity.MutilpleEntity> getClazzes() {
        return clazzes;
    }

    public void setClazzes(List<Clazz.ClassEntity.MutilpleEntity> clazzes) {
        this.clazzes = clazzes;
    }
}
