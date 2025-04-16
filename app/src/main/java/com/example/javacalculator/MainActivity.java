package com.example.javacalculator;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import com.example.javacalculator.databinding.ActivityMainBinding;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    // Variable para view binding
    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Inflar el layout usando View Binding
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Configurar el Toolbar
        setSupportActionBar(binding.toolbar);

        // Configurar el botón para calcular
        binding.btnCalculate.setOnClickListener(v -> {
            try {
                String ip = binding.edtIP.getText().toString().trim();
                int mask = Integer.parseInt(binding.edtMask.getText().toString().trim());
                int subnets = Integer.parseInt(binding.edtSubnets.getText().toString().trim());

                // Se asume que tienes implementadas las clases VLSMCalculator y Subnet
                List<Subnet> results = VLSMCalculator.calculateSubnets(ip, mask, subnets);
                StringBuilder builder = new StringBuilder();
                for (Subnet s : results) {
                    builder.append(s.toString()).append("\n");
                }
                binding.tvResult.setText(builder.toString());
            } catch (Exception e) {
                binding.tvResult.setText("Error: " + e.getMessage());
                Log.e("MainActivity", "Error in calculation", e);
            }
        });
    }
}