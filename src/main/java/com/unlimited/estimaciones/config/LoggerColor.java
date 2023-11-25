package com.unlimited.estimaciones.config;

import org.slf4j.Logger;

public class LoggerColor {
    private final Logger log ;
    private static final String ROJO = "\u001B[31m";
    private static final String VERDE = "\u001B[32m";
    private static final String AZUL = "\u001B[34m";
    private static final String BLANCO = "\u001B[0m";


    public LoggerColor(Logger log) {
        this.log = log;
    }

    public void info(String msg){
        log.info(concatena(BLANCO,msg));
    }
    public void infoGreen(String msg){
        log.info(concatena(VERDE,msg));
    }
    public void infoBlue(String msg){
        log.info(concatena(AZUL,msg));
    }

    public void infoRed(String msg){
        log.info(concatena(ROJO,msg));
    }
    private String concatena(String color, String msg){
        return String.format("%s %s %s",color,msg,color);
    }
}
