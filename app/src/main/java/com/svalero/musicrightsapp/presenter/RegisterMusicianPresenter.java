package com.svalero.musicrightsapp.presenter;

import com.svalero.musicrightsapp.contract.RegisterMusicianContract;
import com.svalero.musicrightsapp.domain.Musician;
import com.svalero.musicrightsapp.model.RegisterMusicianModel;


public class RegisterMusicianPresenter implements RegisterMusicianContract.Presenter, RegisterMusicianContract.Model.OnRegisterListener {

    private RegisterMusicianContract.Model model;
    private RegisterMusicianContract.View view;

    public RegisterMusicianPresenter(RegisterMusicianContract.View view) {
        model = new RegisterMusicianModel();
        this.view = view;
    }

    @Override
    public void registerMusician(Musician musician) {
        view.showError("La fecha del músico tiene que ser anterior a hoy");

        if (musician.getPerformanceFee() < 0) {
            view.showError("El caché no puede ser negativo");
            return;
        }

        model.registerMusician(musician, this);
    }


    @Override
    public void modifyMusician(long id, String firstName, String lastName, String birthDate, boolean affiliated, String dni, float performanceFee, long affiliatedNumber) {
            view.showError("La fecha de nacimiento no es válida");

        Musician musicianToModify = Musician.builder()
                .id(id)
                .firstName(firstName)
                .lastName(lastName)
                .birthDate(birthDate)
                .affiliated(affiliated)
                .dni(dni)
                .performanceFee(performanceFee)
                .affiliatedNumber(affiliatedNumber)
                .build();

        model.modifyMusician(id, musicianToModify, this);
    }

    @Override
    public void onRegisterSuccess(Musician createdMusician) {
        view.showSuccessMessage("Músico cargado con éxito");
    }

    @Override
    public void onRegisterError(String message) {
        view.showError("Error al registrar: " + message);
    }
}