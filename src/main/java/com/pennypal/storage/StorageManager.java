package main.java.com.pennypal.storage;

import com.google.gson.*;
import com.google.gson.reflect.TypeToken;
import main.java.com.pennypal.model.*;
import main.java.com.pennypal.viewmodel.*;

import java.io.*;
import java.lang.reflect.Type;
import java.util.*;

public class StorageManager {
    private static final String FILE_PATH = "data.json";
    private static final Gson gson = new GsonBuilder().setPrettyPrinting().create();

    public static void save(HomeViewModel viewModel) {
        try (FileWriter writer = new FileWriter(FILE_PATH)) {
            Map<String, Object> data = new HashMap<>();
            data.put("balance", viewModel.getBalance());
            data.put("limit", viewModel.getLimit());
            data.put("expenses", viewModel.getRecentExpenses());
            gson.toJson(data, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void load(HomeViewModel viewModel) {
        try (FileReader reader = new FileReader(FILE_PATH)) {
            JsonObject json = gson.fromJson(reader, JsonObject.class);
            double balance = json.get("balance").getAsDouble();
            viewModel.addIncome(balance, "Initial Load");

            if (json.has("limit") && !json.get("limit").isJsonNull()) {
                Limit limit = gson.fromJson(json.get("limit"), Limit.class);
                viewModel.setLimit(limit);
            }

            Type expenseListType = new TypeToken<List<Expense>>() {}.getType();
            List<Expense> expenses = gson.fromJson(json.get("expenses"), expenseListType);
            for (Expense e : expenses) {
                viewModel.addExpense(e);
            }

        } catch (FileNotFoundException e) {
            System.out.println("No save file found. Starting fresh.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
