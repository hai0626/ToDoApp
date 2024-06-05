package com.example.todoapp.views;

import androidx.lifecycle.ViewModel;

import com.example.todoapp.models.Task;
import com.example.todoapp.repository.UsersRepository;

public class UserViewModel extends ViewModel {

    UsersRepository usersRepository;

    public void checkLogin(String username, String password){
        usersRepository.checkLogin(username,password);
    }
}
