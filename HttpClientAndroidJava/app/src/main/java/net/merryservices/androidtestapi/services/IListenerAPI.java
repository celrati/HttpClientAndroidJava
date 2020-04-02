package net.merryservices.androidtestapi.services;

import net.merryservices.androidtestapi.model.Personnage;

import java.util.ArrayList;

public interface IListenerAPI {

    public  void receivePersonnages(ArrayList<Personnage> personnages);
}
