package com.example.miappbiker;

import android.content.Intent;
import android.os.Bundle;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.List;

public class HomeActivity extends AppCompatActivity {

    private CardView btnCatMotos;
    private CardView btnCatCascos;
    private CardView btnCatIndumentaria;
    private EditText etSearch;

    private static class SearchProduct {
        String name;
        String category;
        int imageRes;
        String price;
        String[] specs;

        SearchProduct(String name, String category, int imageRes, String price, String[] specs) {
            this.name = name;
            this.category = category;
            this.imageRes = imageRes;
            this.price = price;
            this.specs = specs;
        }
    }

    private final List<SearchProduct> allProducts = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_home);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.home_root), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        initProductsList();

        btnCatMotos = findViewById(R.id.btn_cat_motos);
        btnCatCascos = findViewById(R.id.btn_cat_cascos);
        btnCatIndumentaria = findViewById(R.id.btn_cat_indumentaria);
        etSearch = findViewById(R.id.et_search);

        btnCatMotos.setOnClickListener(v -> {
            Intent intent = new Intent(HomeActivity.this, MotosCatalogActivity.class);
            startActivity(intent);
        });

        btnCatCascos.setOnClickListener(v -> {
            Intent intent = new Intent(HomeActivity.this, CascosCatalogActivity.class);
            startActivity(intent);
        });

        btnCatIndumentaria.setOnClickListener(v -> {
            Intent intent = new Intent(HomeActivity.this, IndumentariaCatalogActivity.class);
            startActivity(intent);
        });

        setupSearch();
    }

    private void initProductsList() {
        // Motos
        allProducts.add(new SearchProduct("KTM POWER 250CC", "Motos", R.drawable.moto_ktm_power, "$4,800 USD", new String[]{"Cilindrada 250cc", "Motor a varilla", "Tanque 13 Lts"}));
        allProducts.add(new SearchProduct("KTM Enduro 250 XC", "Motos", R.drawable.moto_ktm_enduro, "$5,200 USD", new String[]{"Cilindrada 250cc 2T", "Inyeccion TPI", "Suspension WP"}));
        allProducts.add(new SearchProduct("Kawasaki Z250 Naked", "Motos", R.drawable.moto_kawasaki_z, "$5,800 USD", new String[]{"Cilindrada 249cc", "Bicilindrico", "Frenos ABS"}));
        allProducts.add(new SearchProduct("Honda CRF 450R", "Motos", R.drawable.moto_honda_crf, "$9,800 USD", new String[]{"Cilindrada 449cc", "Motor 4T Unicam", "Chasis Aluminio"}));
        allProducts.add(new SearchProduct("BMW S1000RR Superbike", "Motos", R.drawable.moto_bmw_s1000rr, "$22,500 USD", new String[]{"Cilindrada 999cc", "205 HP", "ABS Pro"}));
        allProducts.add(new SearchProduct("Kawasaki Ninja ZX-6R", "Motos", R.drawable.moto_kawasaki_ninja, "$14,800 USD", new String[]{"Cilindrada 636cc", "130 HP", "Frenos Nissin"}));
        allProducts.add(new SearchProduct("BMW R 1250 GS Adventure", "Motos", R.drawable.moto_bmw_gs, "$26,900 USD", new String[]{"Cilindrada 1254cc", "Boxer 136 HP", "Tanque 30L"}));
        allProducts.add(new SearchProduct("Ducati Panigale V4", "Motos", R.drawable.moto_ducati_panigale, "$28,300 USD", new String[]{"Cilindrada 1103cc", "214 HP", "Brembo Stylema"}));
        allProducts.add(new SearchProduct("Yamaha YZF-R6", "Motos", R.drawable.moto_yamaha_r6, "$13,900 USD", new String[]{"Cilindrada 599cc", "118 HP", "D-Mode"}));
        allProducts.add(new SearchProduct("City Scooter 125", "Motos", R.drawable.moto_scooter_city, "$1,650 USD", new String[]{"Cilindrada 125cc", "Automatica CVT", "Baul"}));

        // Cascos
        allProducts.add(new SearchProduct("AGV Pista GP RR Carbon", "Cascos", R.drawable.casco_agv_pista, "$1,450 USD", new String[]{"100% Fibra Carbono", "FIM / ECE 22.06"}));
        allProducts.add(new SearchProduct("Shoei X-Fourteen Racing", "Cascos", R.drawable.casco_shoei_x14, "$890 USD", new String[]{"Calota AIM+ 6 capas", "Circuito Pro"}));
        allProducts.add(new SearchProduct("Alpinestars Supertech S-M10", "Cascos", R.drawable.casco_alpinestars_sm10, "$680 USD", new String[]{"Carbono 3K", "MIPS Rotacional"}));
        allProducts.add(new SearchProduct("Bell Moto-9 Flex Off-Road", "Cascos", R.drawable.casco_bell_moto9, "$620 USD", new String[]{"Tri-Matrix", "Forro Flex"}));
        allProducts.add(new SearchProduct("HJC RPHA 11 Pro Carbon", "Cascos", R.drawable.casco_hjc_rpha, "$580 USD", new String[]{"P.I.M. Plus Fibra", "Pinlock"}));
        allProducts.add(new SearchProduct("Arai RX-7V EVO Racing", "Cascos", R.drawable.casco_arai_rx7v, "$1,050 USD", new String[]{"PB-SNC2 Reforzada", "ECE 22.06"}));
        allProducts.add(new SearchProduct("Fox Racing V3 RS Solids", "Cascos", R.drawable.casco_fox_v3, "$520 USD", new String[]{"MIPS D3O", "MCT Fibra"}));
        allProducts.add(new SearchProduct("LS2 Thunder Carbon Pro", "Cascos", R.drawable.casco_ls2_thunder, "$420 USD", new String[]{"Fibra Carbono 6K", "Doble D"}));

        // Indumentaria
        allProducts.add(new SearchProduct("Chaqueta Alpinestars GP Plus", "Indumentaria", R.drawable.gear_chaqueta_cuero, "$520 USD", new String[]{"Cuero Bovino 1.3mm", "Proteccion Nucleon"}));
        allProducts.add(new SearchProduct("Guantes Dainese Full Metal 6", "Indumentaria", R.drawable.gear_guantes_racing, "$390 USD", new String[]{"Piel Cabra + Kevlar", "Titanio/Carbono"}));
        allProducts.add(new SearchProduct("Botas Alpinestars Supertech R", "Indumentaria", R.drawable.gear_botas_supertech, "$550 USD", new String[]{"Microfibra Tecnica", "Deslizador TPU"}));
        allProducts.add(new SearchProduct("Mono Dainese Laguna Seca 5", "Indumentaria", R.drawable.gear_traje_monomono, "$1,390 USD", new String[]{"Cuero Vacuno Tutu", "MotoGP Pro"}));
        allProducts.add(new SearchProduct("Pantalon Kevlar Rev'it Jeans", "Indumentaria", R.drawable.gear_pantalon_kevlar, "$230 USD", new String[]{"Cordura Denim", "Proteccion Seesmart"}));
        allProducts.add(new SearchProduct("Chaqueta Dainese Carve Master", "Indumentaria", R.drawable.gear_chaqueta_textil, "$640 USD", new String[]{"Gore-Tex Impermeable", "Termico Desmontable"}));
        allProducts.add(new SearchProduct("Botas Touring Forma Adventure", "Indumentaria", R.drawable.gear_botas_adventure, "$280 USD", new String[]{"Cuero Vintage", "Drytex Impermeable"}));
        allProducts.add(new SearchProduct("Chaleco Airbag Alpinestars Tech-Air", "Indumentaria", R.drawable.gear_chaleco_airbag, "$750 USD", new String[]{"Autonomo Sin Cables", "Despliegue 20-40ms"}));
    }

    private void setupSearch() {
        if (etSearch == null) return;

        etSearch.setOnEditorActionListener((v, actionId, event) -> {
            if (actionId == EditorInfo.IME_ACTION_SEARCH || actionId == EditorInfo.IME_ACTION_DONE) {
                performSearch(etSearch.getText().toString().trim());
                return true;
            }
            return false;
        });
    }

    private void performSearch(String query) {
        if (query.isEmpty()) {
            Toast.makeText(this, "Escribe un producto o marca para buscar", Toast.LENGTH_SHORT).show();
            return;
        }

        String q = query.toLowerCase();
        List<SearchProduct> matches = new ArrayList<>();
        for (SearchProduct p : allProducts) {
            if (p.name.toLowerCase().contains(q) || p.category.toLowerCase().contains(q)) {
                matches.add(p);
            }
        }

        if (matches.isEmpty()) {
            Toast.makeText(this, "No se encontraron productos para \"" + query + "\"", Toast.LENGTH_SHORT).show();
            return;
        }

        String[] itemTitles = new String[matches.size()];
        for (int i = 0; i < matches.size(); i++) {
            SearchProduct item = matches.get(i);
            itemTitles[i] = item.name + " (" + item.category + ") - " + item.price;
        }

        new AlertDialog.Builder(this)
                .setTitle("Resultados de Búsqueda (" + matches.size() + ")")
                .setItems(itemTitles, (dialog, which) -> {
                    SearchProduct selected = matches.get(which);
                    Intent intent = new Intent(HomeActivity.this, ProductDetailActivity.class);
                    intent.putExtra(ProductDetailActivity.EXTRA_TITLE, selected.name);
                    intent.putExtra(ProductDetailActivity.EXTRA_IMAGE_RES, selected.imageRes);
                    intent.putExtra(ProductDetailActivity.EXTRA_SPECS, selected.specs);
                    intent.putExtra(ProductDetailActivity.EXTRA_PRICE, selected.price);
                    intent.putExtra(ProductDetailActivity.EXTRA_CATEGORY, selected.category);
                    startActivity(intent);
                })
                .setNegativeButton("Cancelar", null)
                .show();
    }
}
