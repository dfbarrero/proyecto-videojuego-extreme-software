/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Personajes;
import Armas.*;

/**
 *
 * @author razvanvc
 */
public abstract class Personaje {
    
    //Atributos
    protected Arma arma;
    protected int pv;
    protected String nombre;
    protected int destreza;

    //Constructor

    public Personaje(String nombre,int pv,int destreza,Arma arma) {
        this.nombre = nombre;
        this.pv = pv;
        this.destreza = destreza;
        this.arma = arma;
    }

    public Personaje(String nombre,int pv,  int destreza) {
        this.pv = pv;
        this.nombre = nombre;
        this.destreza = destreza;
    }
    
    public Personaje() {
        this.nombre = "Generico";
        this.pv = 100;
        this.destreza = 10;
        //Asigne Arma con el comando setArma(Arma arma)
    }
    
    //Metodos
    
    //Get
    public int getDestreza() {
        return destreza;
    }    
    public String getNombre() {
        return nombre;
    }
    public Arma getArma() {
        return arma;
    }
    public int getPv() {
        return pv;
    }
    
    //Set
    public void setDestreza(int destreza) {
        this.destreza = destreza;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public void setPv(int pv) {
        this.pv = pv;
    }
    public void setArma(Arma arma) {
        this.arma = arma;
    }

    //Extra
    public boolean estaDerrotado(){
        boolean estado = true;
        if (this.pv>0) estado = false;
        return estado;
    }
    public abstract void atacar(Personaje oponente, Personaje tu);
    public abstract void combatir(Personaje atacante, Personaje defensor);


    @Override
    public String toString() {
        return "Personaje{" + "arma=" + arma + ", pv=" + pv + ", nombre=" + nombre + ", destreza=" + destreza + '}';
    }
    
    
}
