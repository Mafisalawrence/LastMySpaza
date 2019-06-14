package com.example.lastmyspaza.Shared.ViewModel;

import com.example.lastmyspaza.Shared.Models.ManagerDetails;
import com.example.lastmyspaza.Shared.Models.Store;

import androidx.lifecycle.ViewModel;

public class RegistrationAccountDetails extends ViewModel {
    private ManagerDetails managerDetails = new ManagerDetails();
    private String password;

    public void setManagerDetails(ManagerDetails managerDetails) {
        this.managerDetails = managerDetails;
    }

    public ManagerDetails getManagerDetails() {
        return managerDetails;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
