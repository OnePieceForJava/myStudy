package com.wusp.lambda.pojo;

/**
 * 专辑，由若干曲目组成
 */
public class Album {
    //专辑名（例如《左轮手枪》）
    private String name;
    //专辑上所有曲目的列表
    private Track[] tracks;
    //参与创作本专辑的艺术家列表
    private Artist[] musicians;
}
