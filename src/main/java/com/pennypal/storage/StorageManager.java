package main.java.com.pennypal.storage;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import main.java.com.pennypal.model.Home;
import main.java.com.pennypal.viewmodel.HomeViewModel;

import java.io.*;
import java.lang.reflect.Type;

public class StorageManager {

    private static final String FILE_PATH = "data.json";
    private static final Gson gson = new Gson();

    public static void load(HomeViewModel viewModel) {
        try (Reader reader = new FileReader(FILE_PATH)) {
            Type type = new TypeToken<Home>() {}.getType();
            Home loadedHome = gson.fromJson(reader, type);
            if (loadedHome != null) {
                viewModel.copyFrom(new HomeViewModel(loadedHome)); // ✅ use constructor that accepts Home
            }
        } catch (IOException e) {
            System.out.println("No data found or error loading data.");
        }
    }

    public static void save(HomeViewModel viewModel) {
        try (Writer writer = new FileWriter(FILE_PATH)) {
            gson.toJson(viewModel.getData(), writer); // ✅ only save the Home model
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
