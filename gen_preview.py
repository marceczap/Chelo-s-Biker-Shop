import base64
import os
import json

helmet_path = r'app/src/main/res/drawable/ic_helmet_logo.png'
store_path = r'app/src/main/res/drawable/img_store_banner.png'
bg_path = r'app/src/main/res/drawable/bg_tire_tracks.png'
motos_path = r'app/src/main/res/drawable/img_cat_motos.png'
cascos_path = r'app/src/main/res/drawable/img_cat_cascos.png'
indum_path = r'app/src/main/res/drawable/img_cat_indumentaria.png'

def to_b64(path):
    if os.path.exists(path):
        with open(path, 'rb') as f:
            return 'data:image/png;base64,' + base64.b64encode(f.read()).decode('utf-8')
    return ''

helmet_b64 = to_b64(helmet_path)
store_b64 = to_b64(store_path)
bg_b64 = to_b64(bg_path)
motos_b64 = to_b64(motos_path)
cascos_b64 = to_b64(cascos_path)
indum_b64 = to_b64(indum_path)

# 10 Motorcycle images in base64
moto_bmw_s1000rr = to_b64(r'app/src/main/res/drawable/moto_bmw_s1000rr.png')
moto_kawasaki_ninja = to_b64(r'app/src/main/res/drawable/moto_kawasaki_ninja.png')
moto_bmw_gs = to_b64(r'app/src/main/res/drawable/moto_bmw_gs.png')
moto_kawasaki_z = to_b64(r'app/src/main/res/drawable/moto_kawasaki_z.png')
moto_honda_crf = to_b64(r'app/src/main/res/drawable/moto_honda_crf.png')
moto_ktm_enduro = to_b64(r'app/src/main/res/drawable/moto_ktm_enduro.png')
moto_ducati = to_b64(r'app/src/main/res/drawable/moto_ducati_panigale.png')
moto_yamaha = to_b64(r'app/src/main/res/drawable/moto_yamaha_r6.png')
moto_ktm_power = to_b64(r'app/src/main/res/drawable/img_moto_1.png')
moto_scooter = to_b64(r'app/src/main/res/drawable/moto_scooter_city.png')

map_lapaz_b64 = to_b64(r'app/src/main/res/drawable/map_thumb_lapaz.png')
map_elalto_b64 = to_b64(r'app/src/main/res/drawable/map_thumb_elalto.png')
map_cochabamba_b64 = to_b64(r'app/src/main/res/drawable/map_thumb_cochabamba.png')

motos_dataset = [
    {
        "id": "moto_1",
        "name": "KTM POWER 250CC",
        "category": "Motocicletas",
        "img": moto_ktm_power,
        "specs": ["CILINDRADA 250CC", "MOTOR A VARILLA", "TANQUE 13 LTS", "FRENOS A DISCO", "TABLERO DIGITAL"],
        "price": "$4,800 USD",
        "price_num": 4800,
        "colors": [
            {"name": "Naranja KTM Racing", "hex": "#FF6600"},
            {"name": "Negro Asfalto Mate", "hex": "#1C1C1C"},
            {"name": "Gris Carbón Titanio", "hex": "#4A4A4A"}
        ]
    },
    {
        "id": "moto_2",
        "name": "KTM Enduro 250 XC",
        "category": "Motocicletas",
        "img": moto_ktm_enduro,
        "specs": ["CILINDRADA 250CC 2T", "INYECCION TPI ELECTRONICA", "SUSPENSION WP XPLOR", "FRENOS BREMBO PRO", "TRANSMISION 6 VEL."],
        "price": "$5,200 USD",
        "price_num": 5200,
        "colors": [
            {"name": "Naranja Factory", "hex": "#FF5500"},
            {"name": "Negro Stealth", "hex": "#181818"},
            {"name": "Blanco Nitro", "hex": "#EDEDED"}
        ]
    },
    {
        "id": "moto_3",
        "name": "Kawasaki Z250 Naked",
        "category": "Motocicletas",
        "img": moto_kawasaki_z,
        "specs": ["CILINDRADA 249CC", "BICILINDRICO DOHC", "FRENOS ABS DISCO", "TRANSMISION 6 VEL.", "REFRIGERACION LIQUIDA"],
        "price": "$5,800 USD",
        "price_num": 5800,
        "colors": [
            {"name": "Verde Lima Ninja", "hex": "#66CC00"},
            {"name": "Negro Ébano Metalizado", "hex": "#111111"},
            {"name": "Gris Grafito Oscuro", "hex": "#383838"}
        ]
    },
    {
        "id": "moto_4",
        "name": "Honda CRF 450R",
        "category": "Motocicletas",
        "img": moto_honda_crf,
        "specs": ["CILINDRADA 449CC", "MOTOR 4T UNICAM", "CHASIS ALUMINIO", "ARRANQUE ELECTRICO", "PESO: 105 KG"],
        "price": "$9,800 USD",
        "price_num": 9800,
        "colors": [
            {"name": "Rojo Victory HRC", "hex": "#D60000"},
            {"name": "Blanco HRC Edición", "hex": "#F0F0F0"},
            {"name": "Negro Competición", "hex": "#1F1F1F"}
        ]
    },
    {
        "id": "moto_5",
        "name": "City Scooter 125",
        "category": "Motocicletas",
        "img": moto_scooter,
        "specs": ["CILINDRADA 125CC", "AUTOMATICA CVT", "CONSUMO 45 KM/L", "FRENO DISCO DEL.", "BAUL BAJO ASIENTO"],
        "price": "$1,650 USD",
        "price_num": 1650,
        "colors": [
            {"name": "Azul Zafiro Metálico", "hex": "#1A56A6"},
            {"name": "Plata Mercurio", "hex": "#B0B0B0"},
            {"name": "Negro Perla", "hex": "#141414"}
        ]
    },
    {
        "id": "moto_6",
        "name": "BMW S1000RR Superbike",
        "category": "Motocicletas",
        "img": moto_bmw_s1000rr,
        "specs": ["CILINDRADA 999CC", "POTENCIA: 205 HP", "ABS PRO / DTC", "LAUNCH CONTROL", "QUICKSHIFTER PRO"],
        "price": "$22,500 USD",
        "price_num": 22500,
        "colors": [
            {"name": "Motorsport Tricolor", "hex": "#FFFFFF"},
            {"name": "Negro Tormenta Metal", "hex": "#1B1B1B"},
            {"name": "Gris Hockenheim Silver", "hex": "#8C929D"}
        ]
    },
    {
        "id": "moto_7",
        "name": "Kawasaki Ninja ZX-6R",
        "category": "Motocicletas",
        "img": moto_kawasaki_ninja,
        "specs": ["CILINDRADA 636CC", "POTENCIA: 130 HP", "FRENOS NISSIN ABS", "CONTROL KTRC", "CHASIS ALUMINIO"],
        "price": "$14,800 USD",
        "price_num": 14800,
        "colors": [
            {"name": "Verde KRT Racing", "hex": "#5CB800"},
            {"name": "Negro Flat Spark", "hex": "#151515"},
            {"name": "Blanco Perla Robótico", "hex": "#EAEAEA"}
        ]
    },
    {
        "id": "moto_8",
        "name": "BMW R 1250 GS Adventure",
        "category": "Motocicletas",
        "img": moto_bmw_gs,
        "specs": ["CILINDRADA 1254CC", "MOTOR BOXER 136 HP", "TANQUE 30 LITROS", "SUSPENSION ESA", "CARDAN REFORZADO"],
        "price": "$26,900 USD",
        "price_num": 26900,
        "colors": [
            {"name": "Azul Racing Triple Black", "hex": "#1B2A4A"},
            {"name": "Arena Kalahari Exclusive", "hex": "#C2B280"},
            {"name": "Gris Hielo Mate", "hex": "#7D8288"}
        ]
    },
    {
        "id": "moto_9",
        "name": "Ducati Panigale V4",
        "category": "Motocicletas",
        "img": moto_ducati,
        "specs": ["CILINDRADA 1103CC", "POTENCIA: 214 HP", "ELECTRONICA EVO 2", "BREMBO STYLEMA", "PANTALLA TFT COLOR"],
        "price": "$28,300 USD",
        "price_num": 28300,
        "colors": [
            {"name": "Rojo Ducati Corse", "hex": "#CC0000"},
            {"name": "Blanco Seda Mate", "hex": "#F4F4F4"},
            {"name": "Negro Dark Stealth", "hex": "#111111"}
        ]
    },
    {
        "id": "moto_10",
        "name": "Yamaha YZF-R6",
        "category": "Motocicletas",
        "img": moto_yamaha,
        "specs": ["CILINDRADA 599CC", "POTENCIA: 118 HP", "MODOS D-MODE", "CONTROL TRACCION", "HORQUILLA KYB 43MM"],
        "price": "$13,900 USD",
        "price_num": 13900,
        "colors": [
            {"name": "Azul Icon Racing Blue", "hex": "#0033A0"},
            {"name": "Negro Midnight Raven", "hex": "#121212"},
            {"name": "Gris Tech Kando", "hex": "#68707A"}
        ]
    }
]

# 8 Helmet images in base64
casco_agv = to_b64(r'app/src/main/res/drawable/casco_agv_pista.png')
casco_shoei = to_b64(r'app/src/main/res/drawable/casco_shoei_x14.png')
casco_sm10 = to_b64(r'app/src/main/res/drawable/casco_alpinestars_sm10.png')
casco_bell = to_b64(r'app/src/main/res/drawable/casco_bell_moto9.png')
casco_hjc = to_b64(r'app/src/main/res/drawable/casco_hjc_rpha.png')
casco_arai = to_b64(r'app/src/main/res/drawable/casco_arai_rx7v.png')
casco_fox = to_b64(r'app/src/main/res/drawable/casco_fox_v3.png')
casco_ls2 = to_b64(r'app/src/main/res/drawable/casco_ls2_thunder.png')

helmets_dataset = [
    {
        "id": "casco_1",
        "name": "AGV PISTA GP RR CARBON",
        "category": "Cascos",
        "img": casco_agv,
        "specs": ["100% FIBRA CARBONO", "HOMOLOGADO FIM / ECE 22.06", "SPOILER PRO AERODINAMICO", "CIERRE DOBLE D TITANIO", "PESO: 1450G"],
        "price": "$1,450 USD",
        "price_num": 1450,
        "colors": [
            {"name": "Carbono / Rojo Pista", "hex": "#8A0000"},
            {"name": "Negro Carbono Glossy", "hex": "#1A1A1A"},
            {"name": "Carbono Oro Edición", "hex": "#D4AF37"}
        ]
    },
    {
        "id": "casco_2",
        "name": "SHOEI X-FOURTEEN RACING",
        "category": "Cascos",
        "img": casco_shoei,
        "specs": ["CALOTA AIM+ 6 CAPAS", "PANTALLA CWR-F PINLOCK", "ALERON REGULABLE", "INTERIOR 3D MAX-DRY", "USO CIRCUITO PRO"],
        "price": "$890 USD",
        "price_num": 890,
        "colors": [
            {"name": "Negro Mate / Titanio", "hex": "#2B2B2B"},
            {"name": "Blanco Brillo Aerodinámico", "hex": "#FFFFFF"},
            {"name": "Rojo Marc Márquez 93", "hex": "#CC1100"}
        ]
    },
    {
        "id": "casco_3",
        "name": "ALPINESTARS SUPERTECH S-M10",
        "category": "Cascos",
        "img": casco_sm10,
        "specs": ["CARBONO 3K MULTI-DENSIDAD", "SISTEMA MIPS ROTACIONAL", "VISERA DESMONTABLE", "19 ENTRADAS VENTILACION", "PESO: 1260G"],
        "price": "$680 USD",
        "price_num": 680,
        "colors": [
            {"name": "Blanco / Cyan / Neón", "hex": "#00B4D8"},
            {"name": "Negro Mate Antracita", "hex": "#222222"},
            {"name": "Naranja Flúor MX", "hex": "#FF6B00"}
        ]
    },
    {
        "id": "casco_4",
        "name": "BELL MOTO-9 FLEX OFF-ROAD",
        "category": "Cascos",
        "img": casco_bell,
        "specs": ["COMPUESTO TRI-MATRIX", "FORRO FLEX MULTI-IMPACTO", "CIERRE MAGNETICO", "VENTILACION VELOCITY", "USO ENDURO / MX"],
        "price": "$620 USD",
        "price_num": 620,
        "colors": [
            {"name": "Naranja Factory / Negro", "hex": "#FF5900"},
            {"name": "Amarillo Ácido / Azul", "hex": "#E0E000"},
            {"name": "Blanco Puro Solids", "hex": "#F5F5F5"}
        ]
    },
    {
        "id": "casco_5",
        "name": "HJC RPHA 11 PRO CARBON",
        "category": "Cascos",
        "img": casco_hjc,
        "specs": ["CALOTA P.I.M. PLUS FIBRA", "VISORES OSCURO + PINLOCK", "EXTRACCION EMERGENCIA", "TUNEL DE VIENTO MOTOGP", "CIERRE DOBLE ANILLA"],
        "price": "$580 USD",
        "price_num": 580,
        "colors": [
            {"name": "Rojo / Camo / Blanco", "hex": "#D32F2F"},
            {"name": "Negro Carbono Semi-Mate", "hex": "#262626"},
            {"name": "Azul Eléctrico Racing", "hex": "#0D47A1"}
        ]
    },
    {
        "id": "casco_6",
        "name": "ARAI RX-7V EVO RACING",
        "category": "Cascos",
        "img": casco_arai,
        "specs": ["CALOTA PB-SNC2 REFORZADA", "DIFUSORES TIPO 12 AERO", "PANTALLA VAS-V VARIABLE", "NORMA ECE 22.06 OFICIAL", "ARTESANAL JAPONES"],
        "price": "$1,050 USD",
        "price_num": 1050,
        "colors": [
            {"name": "Blanco / Rojo Pedrosa", "hex": "#E53935"},
            {"name": "Negro Diamond Brillante", "hex": "#111111"},
            {"name": "Gris Nardo Racing", "hex": "#78909C"}
        ]
    },
    {
        "id": "casco_7",
        "name": "FOX RACING V3 RS SOLIDS",
        "category": "Cascos",
        "img": casco_fox,
        "specs": ["SISTEMA MIPS D3O", "CALOTA MCT FIBRA CARBONO", "REJILLAS INYECTADAS", "ALMOHADILLAS PRO", "COMPETICION MX"],
        "price": "$520 USD",
        "price_num": 520,
        "colors": [
            {"name": "Negro / Verde Neón", "hex": "#76FF03"},
            {"name": "Negro / Naranja Flúor", "hex": "#FF6D00"},
            {"name": "Gris Cemento Matte", "hex": "#616161"}
        ]
    },
    {
        "id": "casco_8",
        "name": "LS2 THUNDER CARBON PRO",
        "category": "Cascos",
        "img": casco_ls2,
        "specs": ["100% FIBRA CARBONO 6K", "PANTALLA RACING CLASE A", "DESBLOQUEO RAPIDO", "CIERRE DOBLE ANILLA D", "PESO: 1350G"],
        "price": "$420 USD",
        "price_num": 420,
        "colors": [
            {"name": "Fibra Carbono / Amarillo Flúor", "hex": "#CCFF00"},
            {"name": "Fibra Carbono / Rojo Sport", "hex": "#C62828"},
            {"name": "Carbono Puro Satinado", "hex": "#1E1E1E"}
        ]
    }
]

# 8 Gear images in base64
gear_chaqueta_cuero = to_b64(r'app/src/main/res/drawable/gear_chaqueta_cuero.png')
gear_guantes_racing = to_b64(r'app/src/main/res/drawable/gear_guantes_racing.png')
gear_botas_supertech = to_b64(r'app/src/main/res/drawable/gear_botas_supertech.png')
gear_traje_monomono = to_b64(r'app/src/main/res/drawable/gear_traje_monomono.png')
gear_pantalon_kevlar = to_b64(r'app/src/main/res/drawable/gear_pantalon_kevlar.png')
gear_chaqueta_textil = to_b64(r'app/src/main/res/drawable/gear_chaqueta_textil.png')
gear_botas_adventure = to_b64(r'app/src/main/res/drawable/gear_botas_adventure.png')
gear_chaleco_airbag = to_b64(r'app/src/main/res/drawable/gear_chaleco_airbag.png')

gear_dataset = [
    {
        "id": "gear_1",
        "name": "CHAQUETA ALPINESTARS GP PLUS",
        "category": "Indumentaria",
        "img": gear_chaqueta_cuero,
        "specs": ["CUERO BOVINO 1.3MM", "PROTECCION NUCLEON CE", "PANELES STRETCH", "REGULACION CINTURA", "USO RACING / SPORT"],
        "price": "$520 USD",
        "price_num": 520,
        "colors": [
            {"name": "Negro / Rojo Racing", "hex": "#D32F2F"},
            {"name": "Negro Total Black", "hex": "#1A1A1A"},
            {"name": "Negro / Blanco / Flúor", "hex": "#E0E0E0"}
        ]
    },
    {
        "id": "gear_2",
        "name": "GUANTES DAINESE FULL METAL 6",
        "category": "Indumentaria",
        "img": gear_guantes_racing,
        "specs": ["PIEL CABRA + KEVLAR", "NUDILLOS TITANIO/CARBONO", "COSTURAS FIBRA ARAMIDA", "CONTROL DISTORSION DCP", "HOMOLOGACION CE CAT II"],
        "price": "$390 USD",
        "price_num": 390,
        "colors": [
            {"name": "Negro / Rojo Corse", "hex": "#C62828"},
            {"name": "Negro / Blanco Titanium", "hex": "#B0BEC5"},
            {"name": "Negro Stealth Pista", "hex": "#1E1E1E"}
        ]
    },
    {
        "id": "gear_3",
        "name": "BOTAS ALPINESTARS SUPERTECH R",
        "category": "Indumentaria",
        "img": gear_botas_supertech,
        "specs": ["MICROFIBRA TECNICA", "BOTIN INTERNO BIOMECANICO", "DESLIZADOR TPU REEMPLAZABLE", "SUELA AGARRE COMPUESTO", "USO CIRCUITO Y PISTA"],
        "price": "$550 USD",
        "price_num": 550,
        "colors": [
            {"name": "Negro / Rojo / Blanco", "hex": "#E53935"},
            {"name": "Negro / Gris Oscuro", "hex": "#2E2E2E"},
            {"name": "Blanco / Negro Vented", "hex": "#F5F5F5"}
        ]
    },
    {
        "id": "gear_4",
        "name": "MONO DAINESE LAGUNA SECA 5",
        "category": "Indumentaria",
        "img": gear_traje_monomono,
        "specs": ["CUERO VACUNO TUTU", "PLACAS TITANIO HOMBROS", "VENTILACION PERFORADO", "JOROBA CON HIDROBAG", "NIVEL MOTOGP PRO"],
        "price": "$1,390 USD",
        "price_num": 1390,
        "colors": [
            {"name": "Negro / Rojo Flúor / Blanco", "hex": "#FF1744"},
            {"name": "Negro Stealth / Antracita", "hex": "#212121"},
            {"name": "Azul / Blanco Team", "hex": "#1976D2"}
        ]
    },
    {
        "id": "gear_5",
        "name": "PANTALON KEVLAR REV'IT JEANS",
        "category": "Indumentaria",
        "img": gear_pantalon_kevlar,
        "specs": ["CORDURA DENIM 12.5OZ", "PWR|SHIELD ANTI-ABRASION", "PROTECCIONES SEESMART CE", "CORTE SLIM FIT COMODO", "COSTURAS TRIPLES"],
        "price": "$230 USD",
        "price_num": 230,
        "colors": [
            {"name": "Azul Denim Clásico", "hex": "#283593"},
            {"name": "Negro Raw Washed", "hex": "#212121"},
            {"name": "Azul Índigo Claro", "hex": "#5C6BC0"}
        ]
    },
    {
        "id": "gear_6",
        "name": "CHAQUETA DAINESE CARVE MASTER",
        "category": "Indumentaria",
        "img": gear_chaqueta_textil,
        "specs": ["GORE-TEX IMPERMEABLE", "FORRO TERMICO DESMONTABLE", "PROTECCION PRO-ARMOR CE", "VENTILACION PECHO/ESPALDA", "TOURING 4 ESTACIONES"],
        "price": "$640 USD",
        "price_num": 640,
        "colors": [
            {"name": "Negro / Rojo Ebony", "hex": "#B71C1C"},
            {"name": "Gris Glaciar / Negro", "hex": "#78909C"},
            {"name": "Negro Total Gore-Tex", "hex": "#1F1F1F"}
        ]
    },
    {
        "id": "gear_7",
        "name": "BOTAS TOURING FORMA ADVENTURE",
        "category": "Indumentaria",
        "img": gear_botas_adventure,
        "specs": ["CUERO TRATADO VINTAGE", "DRYTEX 100% IMPERMEABLE", "CIERRES GH REGULABLES", "SUELA DOBLE DENSIDAD", "MAXI-TRAIL / ADVENTURE"],
        "price": "$280 USD",
        "price_num": 280,
        "colors": [
            {"name": "Marrón Cuero Vintage", "hex": "#5D4037"},
            {"name": "Negro Aceitado Pro", "hex": "#212121"},
            {"name": "Arena Desierto Café", "hex": "#8D6E63"}
        ]
    },
    {
        "id": "gear_8",
        "name": "CHALECO AIRBAG ALPINESTARS TECH-AIR",
        "category": "Indumentaria",
        "img": gear_chaleco_airbag,
        "specs": ["AUTONOMO SIN CABLES", "6 SENSORES INTEGRADOS", "PROTECCION TORAX/ESPALDA", "CONEXION BLUETOOTH APP", "DESPLIEGUE 20-40 MS"],
        "price": "$750 USD",
        "price_num": 750,
        "colors": [
            {"name": "Negro Malla Transpirable", "hex": "#1A1A1A"},
            {"name": "Gris Carbón Reflectante", "hex": "#424242"}
        ]
    }
]

motos_json = json.dumps(motos_dataset)
helmets_json = json.dumps(helmets_dataset)
gear_json = json.dumps(gear_dataset)

html_content = f"""<!DOCTYPE html>
<html lang="es">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Chelo's Biker Shop - Interactive Preview</title>
  <script src="https://cdn.tailwindcss.com"></script>
  <link rel="preconnect" href="https://fonts.googleapis.com">
  <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
  <link href="https://fonts.googleapis.com/css2?family=Cinzel:wght@700;900&family=Pirata+One&family=Playfair+Display:ital,wght@0,700;0,900;1,700&family=Special+Elite&family=UnifrakturMaguntia&display=swap" rel="stylesheet">

  <style>
    body {{
      background: radial-gradient(circle at center, #2e384d 0%, #171d2b 100%);
      font-family: 'Playfair Display', serif;
      scrollbar-width: none;
      -ms-overflow-style: none;
    }}
    *::-webkit-scrollbar {{
      display: none !important;
      width: 0px !important;
      height: 0px !important;
    }}

    .gothic-arch {{
      font-family: 'UnifrakturMaguntia', 'Pirata One', serif;
    }}
    .vintage-shaded {{
      font-family: 'Playfair Display', 'Cinzel', serif;
      letter-spacing: 1px;
      font-weight: 800;
      color: #111111;
      text-shadow: 1px 1px 0px rgba(0,0,0,0.2);
    }}
    .iniciar-sesion-font {{
      font-family: 'Cinzel', 'Playfair Display', serif;
      letter-spacing: 1px;
    }}
    .typewriter-input {{
      font-family: 'Special Elite', 'Cutive Mono', monospace;
    }}
    .phone-container {{
      width: 390px;
      height: 840px;
      box-shadow: 0 25px 50px -12px rgba(0, 0, 0, 0.5), 0 0 0 10px #1e293b;
      border-radius: 42px;
      overflow: hidden;
      display: flex;
      flex-direction: column;
      position: relative;
      background: #ffffff;
    }}
    .pill-input {{
      background-color: #cccccc;
      border-radius: 9999px;
    }}
    .bg-watermark {{
      position: absolute;
      inset: 0;
      width: 100%;
      height: 100%;
      object-fit: cover;
      opacity: 0.13;
      pointer-events: none;
      z-index: 0;
    }}
    .helmet-watermark {{
      position: absolute;
      bottom: 0px;
      left: -20px;
      width: 330px;
      height: 330px;
      opacity: 0.22;
      pointer-events: none;
      z-index: 0;
    }}
    .helmet-home-watermark {{
      position: absolute;
      bottom: -20px;
      left: 50%;
      transform: translateX(-50%);
      width: 320px;
      height: 320px;
      opacity: 0.15;
      pointer-events: none;
      z-index: 0;
    }}
    .helmet-catalog-watermark {{
      position: absolute;
      bottom: 20px;
      left: -40px;
      width: 360px;
      height: 360px;
      opacity: 0.18;
      pointer-events: none;
      z-index: 0;
    }}

    /* iOS Liquid Glass Effect */
    .liquid-glass-search {{
      background: rgba(255, 255, 255, 0.4);
      backdrop-filter: blur(16px);
      -webkit-backdrop-filter: blur(16px);
      border: 1.5px solid rgba(255, 255, 255, 0.7);
      box-shadow: 0 8px 32px 0 rgba(0, 0, 0, 0.15), inset 0 1px 2px rgba(255, 255, 255, 0.9);
      border-radius: 9999px;
    }}
    .liquid-glass-card {{
      background: linear-gradient(180deg, rgba(0, 0, 0, 0.55) 0%, rgba(0, 0, 0, 0.85) 100%);
      backdrop-filter: blur(2.5px);
      -webkit-backdrop-filter: blur(2.5px);
      border: 1.5px solid rgba(255, 255, 255, 0.20);
      box-shadow: 0 12px 28px -6px rgba(0, 0, 0, 0.6), inset 0 1px 1px rgba(255, 255, 255, 0.25);
      border-radius: 28px;
    }}

    /* Floating Cart Animation */
    @keyframes cartBounce {{
      0%, 100% {{ transform: scale(1); }}
      50% {{ transform: scale(1.18); }}
    }}
    .animate-cart-bounce {{
      animation: cartBounce 0.35s ease-in-out;
    }}
  </style>
</head>
<body class="min-h-screen flex flex-col items-center justify-center p-4">

  <!-- Helper Header with Direct Switcher Pills -->
  <div class="mb-3 text-center text-white max-w-lg">
    <h1 class="text-xl font-extrabold tracking-wide drop-shadow">🏍️ Chelo's Biker Shop</h1>
    <div class="flex flex-wrap items-center justify-center gap-1.5 mt-2">
      <button onclick="goToLogin()" class="px-2.5 py-1 text-[11px] font-bold bg-white/20 hover:bg-white/40 rounded-full transition-all cursor-pointer">P1: Login</button>
      <button onclick="goToRegister()" class="px-2.5 py-1 text-[11px] font-bold bg-white/20 hover:bg-white/40 rounded-full transition-all cursor-pointer">P2: Registro</button>
      <button onclick="goToHome()" class="px-2.5 py-1 text-[11px] font-bold bg-white/20 hover:bg-white/40 rounded-full transition-all cursor-pointer">P3: Home</button>
      <button onclick="goToMotosCatalog()" class="px-2.5 py-1 text-[11px] font-bold bg-white/20 hover:bg-white/40 rounded-full transition-all cursor-pointer">P4: Motos</button>
      <button onclick="goToCascosCatalog()" class="px-2.5 py-1 text-[11px] font-bold bg-white/20 hover:bg-white/40 rounded-full transition-all cursor-pointer">P5: Cascos</button>
      <button onclick="goToIndumentariaCatalog()" class="px-2.5 py-1 text-[11px] font-bold bg-white/20 hover:bg-white/40 rounded-full transition-all cursor-pointer">P6: Ropa</button>
      <button onclick="goToCartScreen()" class="px-2.5 py-1 text-[11px] font-bold bg-amber-500/90 hover:bg-amber-500 rounded-full transition-all cursor-pointer">🛒 Carrito</button>
      <button onclick="goToBranchesSelection()" class="px-2.5 py-1 text-[11px] font-bold bg-blue-600/80 hover:bg-blue-600 rounded-full transition-all cursor-pointer">P7: Sucursales</button>
      <button onclick="selectBranch('La Paz')" class="px-2.5 py-1 text-[11px] font-bold bg-emerald-600/80 hover:bg-emerald-600 rounded-full transition-all cursor-pointer">P6: Mapa</button>
    </div>
  </div>

  <div class="phone-container select-none text-gray-900 p-0 flex flex-col justify-between relative overflow-hidden">
    
    <!-- ============================================== -->
    <!-- FLOATING SHOPPING CART BUTTON (CARRITO FLOTANTE) -->
    <!-- ============================================== -->
    <div id="floatingCartContainer" class="hidden absolute bottom-20 right-4 z-40">
      <button 
        id="floatingCartBtn"
        onclick="goToCartScreen()" 
        title="Ver Carrito de Compras"
        class="w-14 h-14 rounded-full bg-gradient-to-tr from-amber-600 to-amber-500 text-white shadow-2xl flex items-center justify-center transform hover:scale-108 active:scale-95 transition-all border-2 border-white/80 cursor-pointer group"
      >
        <svg class="w-7 h-7 fill-none stroke-current stroke-[2.2]" viewBox="0 0 24 24">
          <path stroke-linecap="round" stroke-linejoin="round" d="M3 3h2l.4 2M7 13h10l4-8H5.4M7 13L5.4 5M7 13l-2.293 2.293c-.63.63-.184 1.707.707 1.707H17m0 0a2 2 0 100 4 2 2 0 000-4zm-8 2a2 2 0 11-4 0 2 2 0 014 0z"/>
        </svg>

        <!-- Dynamic Counter Badge -->
        <span 
          id="floatingCartBadge" 
          class="absolute -top-1.5 -right-1.5 bg-black text-white text-[11px] font-black w-6 h-6 rounded-full flex items-center justify-center shadow-md border-2 border-white transform transition-transform"
        >
          0
        </span>
      </button>
    </div>

    <!-- ============================================== -->
    <!-- PANTALLA 1: INICIO DE SESIÓN / PRINCIPAL -->
    <!-- ============================================== -->
    <div id="screenLogin" class="flex flex-col items-center justify-between h-full w-full p-6 relative">
      <img src="{bg_b64}" class="bg-watermark" alt="Fondo Huellas" />

      <!-- Top Brand Logo -->
      <div class="flex flex-col items-center mt-1 z-10 w-full">
        <div class="w-64 h-16 flex items-center justify-center -mb-2">
          <svg viewBox="0 0 260 70" class="w-full h-full overflow-visible">
            <path id="archCurve" d="M 15 65 A 130 55 0 0 1 245 65" fill="transparent" />
            <text class="gothic-arch" font-size="28" font-weight="bold" fill="#000000" text-anchor="middle">
              <textPath href="#archCurve" startOffset="50%">
                Chelo's Biker Shop
              </textPath>
            </text>
          </svg>
        </div>

        <img src="{helmet_b64}" class="w-24 h-24 object-contain my-0.5 transform -rotate-3" alt="Helmet Logo" />
        <p class="gothic-arch text-lg font-bold text-gray-900 -mt-1">Est. 2026</p>
      </div>

      <!-- Store Showroom Banner with Rounded Corners -->
      <div class="w-full max-w-[310px] h-36 rounded-3xl overflow-hidden shadow-md my-1.5 border border-gray-200 z-10">
        <img src="{store_b64}" class="w-full h-full object-cover" alt="Tienda de Motos" />
      </div>

      <h3 class="vintage-shaded text-lg text-center z-10">Tienda de Motos</h3>

      <!-- Iniciar Sesión Section -->
      <div class="w-full max-w-[280px] flex flex-col items-center space-y-2 mt-0.5 z-10">
        <h4 class="iniciar-sesion-font text-sm font-bold text-gray-900">Iniciar Sesión</h4>

        <input 
          type="email" 
          id="emailInput"
          placeholder="correo"
          value="marcelo@chelobiker.com"
          onkeydown="if(event.key==='Enter') handleLogin()"
          class="w-full h-9 px-5 pill-input typewriter-input text-xs text-gray-800 placeholder-gray-600 focus:outline-none focus:ring-2 focus:ring-black transition-all text-left shadow-inner"
        />

        <input 
          type="password" 
          id="passwordInput"
          placeholder="contraseña" 
          value="123456"
          onkeydown="if(event.key==='Enter') handleLogin()"
          class="w-full h-9 px-5 pill-input typewriter-input text-xs text-gray-800 placeholder-gray-600 focus:outline-none focus:ring-2 focus:ring-black transition-all text-left shadow-inner"
        />

        <p class="text-[11px] text-gray-800 font-serif pt-0.5">
          No tienes una cuenta? 
          <button type="button" onclick="goToRegister()" class="text-[#0088ff] hover:underline font-bold cursor-pointer">REGISTRATE</button>
        </p>

        <!-- Main Action Buttons (Button + Circular Arrow ➔) -->
        <div class="flex items-center space-x-2.5 w-full justify-center mt-1">
          <button 
            type="button"
            onclick="handleLogin()" 
            class="px-7 py-2.5 bg-gray-900 hover:bg-black active:scale-95 text-white font-serif font-bold text-xs rounded-full tracking-wider transition-all shadow-md cursor-pointer"
          >
            INGRESAR
          </button>
          
          <button 
            type="button"
            id="btnLoginArrowWeb"
            onclick="handleLogin()" 
            title="Iniciar Sesión"
            class="w-11 h-11 rounded-full bg-[#888888] hover:bg-black active:scale-90 flex items-center justify-center text-white shadow-lg transition-all cursor-pointer"
          >
            <svg class="w-6 h-6" fill="none" stroke="currentColor" stroke-width="2.5" viewBox="0 0 24 24">
              <path stroke-linecap="round" stroke-linejoin="round" d="M14 5l7 7m0 0l-7 7m7-7H3"/>
            </svg>
          </button>
        </div>
      </div>

      <!-- Social Media Footer -->
      <div class="flex flex-col items-center mt-1 mb-1 z-10">
        <p class="iniciar-sesion-font text-xs font-bold text-gray-800 mb-1.5">Siguenos</p>
        <div class="flex items-center space-x-4">
          <a href="https://www.facebook.com" target="_blank" class="w-9 h-9 rounded-full bg-[#1877f2] flex items-center justify-center text-white shadow hover:opacity-90 active:scale-95 transition-all cursor-pointer">
            <svg class="w-5 h-5 fill-current" viewBox="0 0 24 24"><path d="M24 12.073c0-6.627-5.373-12-12-12s-12 5.373-12 12c0 5.99 4.388 10.954 10.125 11.854v-8.385H7.078v-3.47h3.047V9.43c0-3.007 1.792-4.669 4.533-4.669 1.312 0 2.686.235 2.686.235v2.953H15.83c-1.491 0-1.956.925-1.956 1.874v2.25h3.328l-.532 3.47h-2.796v8.385C19.612 23.027 24 18.062 24 12.073z"/></svg>
          </a>
          <a href="https://www.instagram.com" target="_blank" class="w-9 h-9 rounded-full bg-gradient-to-tr from-yellow-400 via-red-500 to-purple-600 flex items-center justify-center text-white shadow hover:opacity-90 active:scale-95 transition-all cursor-pointer">
            <svg class="w-5 h-5 fill-current" viewBox="0 0 24 24"><path d="M12 2.163c3.204 0 3.584.012 4.85.07 3.252.148 4.771 1.691 4.919 4.919.058 1.265.069 1.645.069 4.849 0 3.205-.012 3.584-.069 4.849-.149 3.225-1.664 4.771-4.919 4.919-1.266.058-1.644.07-4.85.07-3.204 0-3.584-.012-4.849-.07-3.26-.149-4.771-1.699-4.919-4.92-.058-1.265-.07-1.644-.07-4.849 0-3.204.013-3.583.07-4.849.149-3.227 1.664-4.771 4.919-4.919 1.266-.057 1.645-.069 4.849-.069zm0-2.163c-3.259 0-3.667.014-4.947.072-4.358.2-6.78 2.618-6.98 6.98-.059 1.281-.073 1.689-.073 4.948 0 3.259.014 3.668.072 4.948.2 4.358 2.618 6.78 6.98 6.98 1.281.058 1.689.072 4.948.072 3.259 0 3.668-.014 4.948-.072 4.354-.2 6.782-2.618 6.979-6.98.059-1.28.073-1.689.073-4.948 0-3.259-.014-3.667-.072-4.947-.196-4.354-2.617-6.78-6.979-6.98-1.281-.059-1.69-.073-4.949-.073zm0 5.838c-3.403 0-6.162 2.759-6.162 6.162s2.759 6.163 6.162 6.163 6.162-2.759 6.162-6.163c0-3.403-2.759-6.162-6.162-6.162zm0 10.162c-2.209 0-4-1.79-4-4 0-2.209 1.791-4 4-4s4 1.791 4 4c0 2.21-1.791 4-4 4zm6.406-11.845c-.796 0-1.441.645-1.441 1.44s.645 1.44 1.441 1.44c.795 0 1.439-.645 1.439-1.44s-.644-1.44-1.439-1.44z"/></svg>
          </a>
          <a href="https://www.youtube.com" target="_blank" class="w-9 h-9 rounded-full bg-[#ff0000] flex items-center justify-center text-white shadow hover:opacity-90 active:scale-95 transition-all cursor-pointer">
            <svg class="w-5 h-5 fill-current" viewBox="0 0 24 24"><path d="M23.498 6.186a3.016 3.016 0 0 0-2.122-2.136C19.505 3.545 12 3.545 12 3.545s-7.505 0-9.377.505A3.017 3.017 0 0 0 .502 6.186C0 8.07 0 12 0 12s0 3.93.502 5.814a3.016 3.016 0 0 0 2.122 2.136c1.871.505 9.376.505 9.376.505s7.505 0 9.377-.505a3.015 3.015 0 0 0 2.122-2.136C24 15.93 24 12 24 12s0-3.93-.502-5.814zM9.545 15.568V8.432L15.818 12l-6.273 3.568z"/></svg>
          </a>
        </div>
      </div>
    </div>

    <!-- ============================================== -->
    <!-- PANTALLA 2: REGISTRO -->
    <!-- ============================================== -->
    <div id="screenRegister" class="hidden flex flex-col justify-between h-full w-full p-6 relative pb-4">
      <img src="{helmet_b64}" class="helmet-watermark" alt="Casco Watermark" />

      <div class="flex items-center justify-between w-full pt-1 z-10">
        <button onclick="goToLogin()" class="p-2 -ml-2 text-gray-900 hover:opacity-70 active:scale-90 transition-all cursor-pointer">
          <svg class="w-7 h-7" fill="none" stroke="currentColor" stroke-width="2.5" viewBox="0 0 24 24"><path stroke-linecap="round" stroke-linejoin="round" d="M10 19l-7-7m0 0l7-7m-7 7h18"/></svg>
        </button>
        <h2 class="text-2xl font-serif font-bold text-gray-900 -ml-2">Registro</h2>
        <div class="flex flex-col items-center">
          <img src="{helmet_b64}" class="w-9 h-9 object-contain" alt="Logo" />
          <span class="gothic-arch text-[10px] font-bold text-gray-900 -mt-0.5">Est. 2026</span>
        </div>
      </div>

      <div class="flex flex-col items-center w-full justify-center flex-1 pt-6 pb-2 z-10 space-y-7">
        <div class="flex flex-col items-center w-full max-w-[280px] space-y-3.5">
          <p class="text-sm font-serif font-bold text-gray-800 text-center">Ingresa tu correo y contraseña</p>
          <input 
            type="email" 
            id="regEmailInput" 
            placeholder="correo" 
            value="nuevo_biker@chelo.com"
            onkeydown="if(event.key==='Enter') handleRegisterSubmit()"
            class="w-full h-11 px-5 pill-input typewriter-input text-xs text-gray-800 placeholder-gray-500 focus:outline-none focus:ring-2 focus:ring-black shadow-inner" 
          />
          <input 
            type="password" 
            id="regPasswordInput" 
            placeholder="contraseña" 
            value="123456"
            onkeydown="if(event.key==='Enter') handleRegisterSubmit()"
            class="w-full h-11 px-5 pill-input typewriter-input text-xs text-gray-800 placeholder-gray-500 focus:outline-none focus:ring-2 focus:ring-black shadow-inner" 
          />
          <button 
            type="button"
            onclick="handleRegisterSubmit()" 
            class="px-11 py-2.5 bg-gray-900 hover:bg-black active:scale-95 text-white font-serif font-bold text-xs rounded-full tracking-wider transition-all mt-1 shadow-md cursor-pointer"
          >
            ACCEDER
          </button>
        </div>

        <div class="flex flex-col items-center w-full space-y-5">
          <p class="text-xs font-serif font-bold text-gray-800 text-center">o Accede desde una cuenta existente</p>
          <div class="flex flex-col items-center space-y-4">
            <button type="button" onclick="showToast('¡Conectado con Google! 🌐'); goToHome();" class="transform hover:scale-110 active:scale-95 transition-all p-1 cursor-pointer" title="Google">
              <svg class="w-12 h-12" viewBox="0 0 48 48"><path fill="#EA4335" d="M24 9.5c3.54 0 6.71 1.22 9.21 3.6l6.85-6.85C35.9 2.38 30.47 0 24 0 14.66 0 6.61 5.38 2.69 13.22l7.98 6.19C12.54 13.72 17.77 9.5 24 9.5z"/><path fill="#4285F4" d="M46.98 24.55c0,-1.57-.15-3.09-.38-4.55H24v9.02h12.94c-.58 2.96-2.26 5.48-4.78 7.18l7.73 6c4.51-4.18 7.09-10.36 7.09-17.65z"/><path fill="#FBBC05" d="M10.67 28.59c-.48-1.45-.76-2.99-.76-4.59s.28-3.14.76-4.59l-7.98-6.19C0.97 16.63 0 20.2 0 24c0 3.8.97 7.37 2.69 10.78l7.98-6.19z"/><path fill="#34A853" d="M24 48c6.48 0 11.93-2.13 15.89-5.81l-7.73-6c-2.15 1.45-4.92 2.3-8.16 2.3-6.23 0-11.46-4.22-13.33-9.91l-7.98 6.19C6.61 42.62 14.66 48 24 48z"/></svg>
            </button>
            <button type="button" onclick="showToast('¡Conectado con Apple ID! 🍏'); goToHome();" class="transform hover:scale-110 active:scale-95 transition-all p-1 flex items-center justify-center cursor-pointer" title="Apple">
              <svg viewBox="0 0 384 512" class="w-10 h-10 fill-black drop-shadow-xs"><path d="M318.7 268.7c-.2-36.7 16.4-64.4 50-84.8-18.8-26.9-47.2-41.7-84.7-44.6-35.5-2.8-74.3 20.7-88.5 20.7-15 0-49.4-19.7-76.4-19.7C63.3 141.2 4 184.8 4 273.5q0 39.3 14.4 81.2c12.8 36.7 59 126.7 107.2 125.2 25.2-.6 43-17.9 75.8-17.9 31.8 0 48.3 17.9 76.4 17.9 48.6-.7 90.4-82.5 102.6-119.3-65.2-30.7-61.7-90-61.7-91.9zm-56.6-164.2c27.3-32.4 24.8-61.9 24-72.5-24.1 1.4-52 16.4-67.9 34.9-17.5 19.8-27.8 44.3-25.6 71.9 26.1 2 49.9-11.4 69.5-34.3z"/></svg>
            </button>
            <button type="button" onclick="showToast('¡Conectado con Facebook! 👤'); goToHome();" class="transform hover:scale-110 active:scale-95 transition-all p-1 cursor-pointer" title="Facebook">
              <svg viewBox="0 0 40 40" class="w-12 h-12"><path fill="#1877F2" d="M20,0C8.954,0 0,8.954 0,20s8.954,20 20,20s20,-8.954 20,-20S31.046,0 20,0z"/><path fill="#FFFFFF" d="M22.5,21.5h3.5l0.5,-4h-4v-2.5c0,-1.1 0.3,-1.8 1.9,-1.8h2.1V9.6c-0.4,-0.1 -1.6,-0.2 -3.1,-0.2c-3.1,0 -5.2,1.9 -5.2,5.3v2.8h-3.4v4h3.4v10.2c0.7,0.1 1.4,0.2 2.1,0.2s1.4,-0.1 2.1,-0.2V21.5z"/></svg>
            </button>
          </div>
        </div>
      </div>
    </div>

    <!-- ============================================== -->
    <!-- PANTALLA 3: HOME / CATEGORÍAS (MINIMALISTA ELEGANTE) -->
    <!-- ============================================== -->
    <div id="screenHome" class="hidden flex flex-col h-full w-full relative overflow-y-auto bg-white">
      <img src="{helmet_b64}" class="helmet-home-watermark" alt="Watermark Helmet" />

      <!-- Top Header Banner -->
      <div class="relative w-full h-52 rounded-b-[38px] overflow-hidden shadow-lg flex flex-col justify-between p-5 shrink-0 z-10">
        <img src="{store_b64}" class="absolute inset-0 w-full h-full object-cover" alt="Banner" />
        <div class="absolute inset-0 bg-black/55 backdrop-blur-[1px]"></div>

        <!-- Top Header Navigation with HOME Centered -->
        <div class="relative z-10 flex items-center justify-between w-full">
          <button onclick="goToLogin()" class="px-2.5 py-1 bg-black/40 hover:bg-black/60 rounded-full text-white text-[11px] font-bold flex items-center space-x-1 backdrop-blur-xs cursor-pointer z-10">
            <span>←</span><span>Salir</span>
          </button>
          
          <!-- Perfectly Centered HOME & Logo -->
          <div class="absolute inset-x-0 flex items-center justify-center space-x-2 pointer-events-none">
            <span class="font-bold text-white text-base tracking-widest drop-shadow font-serif">HOME</span>
            <img src="{helmet_b64}" class="w-8 h-8 object-contain filter invert drop-shadow" alt="Logo" />
          </div>

          <div class="w-12"></div>
        </div>

        <!-- Liquid Glass Functional Search Bar -->
        <div class="relative z-10 w-full h-11 liquid-glass-search flex items-center px-4 space-x-2 mb-1">
          <svg class="w-4 h-4 text-gray-700 shrink-0" fill="none" stroke="currentColor" stroke-width="2.5" viewBox="0 0 24 24"><path stroke-linecap="round" stroke-linejoin="round" d="M21 21l-6-6m2-5a7 7 0 11-14 0 7 7 0 0114 0z"/></svg>
          <input 
            type="text" 
            id="homeSearchInput"
            oninput="handleHomeSearch(this.value)"
            placeholder="Buscar motos, cascos, repuestos..." 
            class="w-full bg-transparent text-xs font-semibold text-gray-900 placeholder-gray-600 focus:outline-none" 
          />
          <button id="btnHomeSearchClear" onclick="clearHomeSearch()" class="hidden text-gray-500 hover:text-black p-1 cursor-pointer">
            <svg class="w-4 h-4" fill="none" stroke="currentColor" stroke-width="2.5" viewBox="0 0 24 24"><path stroke-linecap="round" stroke-linejoin="round" d="M6 18L18 6M6 6l12 12"/></svg>
          </button>
        </div>
      </div>

      <!-- Main Categories Container (Darkened / Opacados) -->
      <div id="homeCategoriesContainer" class="flex-1 flex flex-col justify-around px-6 py-5 space-y-4 z-10">
        <!-- 1. MOTOCICLETAS Button -->
        <button onclick="goToMotosCatalog()" class="relative w-full h-28 rounded-[28px] overflow-hidden group active:scale-95 transform transition-all duration-300 shadow-xl cursor-pointer">
          <img src="{motos_b64}" class="absolute inset-0 w-full h-full object-cover filter brightness-[0.52] contrast-[1.05] group-hover:scale-105 transition-transform duration-500" alt="Motocicletas" />
          <div class="absolute inset-0 liquid-glass-card flex items-center justify-center p-3 group-hover:bg-black/60 transition-colors">
            <span class="text-white font-black text-lg tracking-widest drop-shadow-[0_2px_4px_rgba(0,0,0,0.9)] font-sans">MOTOCICLETAS</span>
          </div>
        </button>

        <!-- 2. CASCOS Button -->
        <button onclick="goToCascosCatalog()" class="relative w-full h-28 rounded-[28px] overflow-hidden group active:scale-95 transform transition-all duration-300 shadow-xl cursor-pointer">
          <img src="{cascos_b64}" class="absolute inset-0 w-full h-full object-cover filter brightness-[0.52] contrast-[1.05] group-hover:scale-105 transition-transform duration-500" alt="Cascos" />
          <div class="absolute inset-0 liquid-glass-card flex items-center justify-center p-3 group-hover:bg-black/60 transition-colors">
            <span class="text-white font-black text-lg tracking-widest drop-shadow-[0_2px_4px_rgba(0,0,0,0.9)] font-sans">CASCOS</span>
          </div>
        </button>

        <!-- 3. INDUMENTARIA Button -->
        <button onclick="goToIndumentariaCatalog()" class="relative w-full h-28 rounded-[28px] overflow-hidden group active:scale-95 transform transition-all duration-300 shadow-xl cursor-pointer">
          <img src="{indum_b64}" class="absolute inset-0 w-full h-full object-cover filter brightness-[0.52] contrast-[1.05] group-hover:scale-105 transition-transform duration-500" alt="Indumentaria" />
          <div class="absolute inset-0 liquid-glass-card flex items-center justify-center p-3 group-hover:bg-black/60 transition-colors">
            <span class="text-white font-black text-lg tracking-widest drop-shadow-[0_2px_4px_rgba(0,0,0,0.9)] font-sans">INDUMENTARIA</span>
          </div>
        </button>
      </div>

      <!-- Live Search Results View (Shown dynamically when searching) -->
      <div id="homeSearchResultsContainer" class="hidden flex-1 overflow-y-auto px-4 py-3 space-y-2.5 z-10 pb-16">
        <div class="flex items-center justify-between px-1">
          <span id="homeSearchCount" class="text-xs font-bold text-gray-700 font-serif">0 productos encontrados</span>
          <button onclick="clearHomeSearch()" class="text-[11px] text-gray-500 hover:text-black font-semibold">Cerrar búsqueda</button>
        </div>
        <div id="homeSearchResultsList" class="space-y-2">
          <!-- Dynamically injected search matches -->
        </div>
      </div>

    </div>

    <!-- ============================================================== -->
    <!-- PANTALLA 4: CATÁLOGO DE MOTOS (DISPONIBLES) -->
    <!-- ============================================================== -->
    <div id="screenMotosCatalog" class="hidden flex flex-col h-full w-full relative bg-white overflow-hidden">
      <img src="{helmet_b64}" class="helmet-catalog-watermark" alt="Casco Watermark" />

      <!-- Top Header Bar -->
      <div class="flex items-center justify-between px-5 pt-4 pb-2 z-10 bg-white/90 backdrop-blur-xs border-b border-gray-100 shrink-0">
        <div class="flex items-center space-x-2">
          <button onclick="goToHome()" class="text-gray-900 font-bold p-1 hover:opacity-70 active:scale-90 transition-all cursor-pointer">
            <svg class="w-5 h-5" fill="none" stroke="currentColor" stroke-width="2.5" viewBox="0 0 24 24"><path stroke-linecap="round" stroke-linejoin="round" d="M15 19l-7-7 7-7"/></svg>
          </button>
          <h2 class="vintage-shaded text-xl font-bold text-gray-900">Motos Disponibles</h2>
        </div>
        <div class="flex items-center space-x-1.5 opacity-60">
          <img src="{helmet_b64}" class="w-6 h-6 object-contain" alt="Logo" />
        </div>
      </div>

      <!-- 2-Column Scrollable Grid -->
      <div class="flex-1 overflow-y-auto p-3.5 z-10 pb-16">
        <div id="motosGrid" class="grid grid-cols-2 gap-3.5">
          <!-- Dynamically populated motos -->
        </div>
      </div>

      <!-- Bottom Navigation Bar -->
      <div class="h-16 bg-white border-t border-gray-100 flex items-center justify-around px-8 z-30 shadow-2xl shrink-0">
        <button onclick="showToast('Ruta: Perfil 👤')" class="flex flex-col items-center justify-center p-2 text-gray-400 hover:text-black transition-all cursor-pointer">
          <svg class="w-7 h-7" fill="currentColor" viewBox="0 0 24 24"><path d="M12 12c2.21 0 4-1.79 4-4s-1.79-4-4-4-4 1.79-4 4 1.79 4 4 4zm0 2c-2.67 0-8 1.34-8 4v2h16v-2c0-2.66-5.33-4-8-4z"/></svg>
        </button>
        <button onclick="goToHome()" class="flex flex-col items-center justify-center p-2 text-black transition-all scale-110 cursor-pointer">
          <svg class="w-8 h-8" fill="currentColor" viewBox="0 0 24 24"><path d="M10 20v-6h4v6h5v-8h3L12 3 2 12h3v8z"/></svg>
        </button>
        <button onclick="goToBranchesSelection()" class="flex flex-col items-center justify-center p-2 text-gray-400 hover:text-black transition-all cursor-pointer">
          <svg class="w-7 h-7" fill="currentColor" viewBox="0 0 24 24"><path d="M3 18h18v-2H3v2zm0-5h18v-2H3v2zm0-7v2h18V6H3z"/></svg>
        </button>
      </div>
    </div>

    <!-- ============================================================== -->
    <!-- PANTALLA 5: CATÁLOGO DE CASCOS (DISPONIBLES) -->
    <!-- ============================================================== -->
    <div id="screenCascosCatalog" class="hidden flex flex-col h-full w-full relative bg-white overflow-hidden">
      <img src="{helmet_b64}" class="helmet-catalog-watermark" alt="Casco Watermark" />

      <!-- Top Header Bar -->
      <div class="flex items-center justify-between px-5 pt-4 pb-2 z-10 bg-white/90 backdrop-blur-xs border-b border-gray-100 shrink-0">
        <div class="flex items-center space-x-2">
          <button onclick="goToHome()" class="text-gray-900 font-bold p-1 hover:opacity-70 active:scale-90 transition-all cursor-pointer">
            <svg class="w-5 h-5" fill="none" stroke="currentColor" stroke-width="2.5" viewBox="0 0 24 24"><path stroke-linecap="round" stroke-linejoin="round" d="M15 19l-7-7 7-7"/></svg>
          </button>
          <h2 class="vintage-shaded text-xl font-bold text-gray-900">Cascos Disponibles</h2>
        </div>
        <div class="flex items-center space-x-1.5 opacity-60">
          <img src="{helmet_b64}" class="w-6 h-6 object-contain" alt="Logo" />
        </div>
      </div>

      <!-- 2-Column Scrollable Grid of Helmets -->
      <div class="flex-1 overflow-y-auto p-3.5 z-10 pb-16">
        <div id="cascosGrid" class="grid grid-cols-2 gap-3.5">
          <!-- Dynamically populated cascos -->
        </div>
      </div>

      <!-- Bottom Navigation Bar -->
      <div class="h-16 bg-white border-t border-gray-100 flex items-center justify-around px-8 z-30 shadow-2xl shrink-0">
        <button onclick="showToast('Ruta: Perfil 👤')" class="flex flex-col items-center justify-center p-2 text-gray-400 hover:text-black transition-all cursor-pointer">
          <svg class="w-7 h-7" fill="currentColor" viewBox="0 0 24 24"><path d="M12 12c2.21 0 4-1.79 4-4s-1.79-4-4-4-4 1.79-4 4 1.79 4 4 4zm0 2c-2.67 0-8 1.34-8 4v2h16v-2c0-2.66-5.33-4-8-4z"/></svg>
        </button>
        <button onclick="goToHome()" class="flex flex-col items-center justify-center p-2 text-black transition-all scale-110 cursor-pointer">
          <svg class="w-8 h-8" fill="currentColor" viewBox="0 0 24 24"><path d="M10 20v-6h4v6h5v-8h3L12 3 2 12h3v8z"/></svg>
        </button>
        <button onclick="goToBranchesSelection()" class="flex flex-col items-center justify-center p-2 text-gray-400 hover:text-black transition-all cursor-pointer">
          <svg class="w-7 h-7" fill="currentColor" viewBox="0 0 24 24"><path d="M3 18h18v-2H3v2zm0-5h18v-2H3v2zm0-7v2h18V6H3z"/></svg>
        </button>
      </div>
    </div>

    <!-- ============================================================== -->
    <!-- PANTALLA 6: CATÁLOGO DE INDUMENTARIA (DISPONIBLES) -->
    <!-- ============================================================== -->
    <div id="screenIndumentariaCatalog" class="hidden flex flex-col h-full w-full relative bg-white overflow-hidden">
      <img src="{helmet_b64}" class="helmet-catalog-watermark" alt="Casco Watermark" />

      <!-- Top Header Bar -->
      <div class="flex items-center justify-between px-5 pt-4 pb-2 z-10 bg-white/90 backdrop-blur-xs border-b border-gray-100 shrink-0">
        <div class="flex items-center space-x-2">
          <button onclick="goToHome()" class="text-gray-900 font-bold p-1 hover:opacity-70 active:scale-90 transition-all cursor-pointer">
            <svg class="w-5 h-5" fill="none" stroke="currentColor" stroke-width="2.5" viewBox="0 0 24 24"><path stroke-linecap="round" stroke-linejoin="round" d="M15 19l-7-7 7-7"/></svg>
          </button>
          <h2 class="vintage-shaded text-xl font-bold text-gray-900">Indumentaria Biker</h2>
        </div>
        <div class="flex items-center space-x-1.5 opacity-60">
          <img src="{helmet_b64}" class="w-6 h-6 object-contain" alt="Logo" />
        </div>
      </div>

      <!-- 2-Column Scrollable Grid of Gear -->
      <div class="flex-1 overflow-y-auto p-3.5 z-10 pb-16">
        <div id="indumGrid" class="grid grid-cols-2 gap-3.5">
          <!-- Dynamically populated gear -->
        </div>
      </div>

      <!-- Bottom Navigation Bar -->
      <div class="h-16 bg-white border-t border-gray-100 flex items-center justify-around px-8 z-30 shadow-2xl shrink-0">
        <button onclick="showToast('Ruta: Perfil 👤')" class="flex flex-col items-center justify-center p-2 text-gray-400 hover:text-black transition-all cursor-pointer">
          <svg class="w-7 h-7" fill="currentColor" viewBox="0 0 24 24"><path d="M12 12c2.21 0 4-1.79 4-4s-1.79-4-4-4-4 1.79-4 4 1.79 4 4 4zm0 2c-2.67 0-8 1.34-8 4v2h16v-2c0-2.66-5.33-4-8-4z"/></svg>
        </button>
        <button onclick="goToHome()" class="flex flex-col items-center justify-center p-2 text-black transition-all scale-110 cursor-pointer">
          <svg class="w-8 h-8" fill="currentColor" viewBox="0 0 24 24"><path d="M10 20v-6h4v6h5v-8h3L12 3 2 12h3v8z"/></svg>
        </button>
        <button onclick="goToBranchesSelection()" class="flex flex-col items-center justify-center p-2 text-gray-400 hover:text-black transition-all cursor-pointer">
          <svg class="w-7 h-7" fill="currentColor" viewBox="0 0 24 24"><path d="M3 18h18v-2H3v2zm0-5h18v-2H3v2zm0-7v2h18V6H3z"/></svg>
        </button>
      </div>
    </div>

    <!-- ============================================================== -->
    <!-- PANTALLA 7: VISTA DE DETALLE DE PRODUCTO (P6 FIGMA) -->
    <!-- ============================================================== -->
    <div id="screenProductDetail" class="hidden flex flex-col h-full w-full relative bg-white overflow-hidden">
      <img src="{helmet_b64}" class="helmet-catalog-watermark" alt="Casco Watermark" />

      <!-- Top Header Bar -->
      <div class="flex items-center justify-between px-5 pt-4 pb-1 z-10 bg-white/90 backdrop-blur-xs shrink-0">
        <button onclick="goBackFromDetail()" class="text-gray-900 font-bold p-1 hover:opacity-70 active:scale-90 transition-all cursor-pointer">
          <svg class="w-6 h-6" fill="none" stroke="currentColor" stroke-width="2.5" viewBox="0 0 24 24"><path stroke-linecap="round" stroke-linejoin="round" d="M15 19l-7-7 7-7"/></svg>
        </button>
        <div class="flex items-center space-x-1.5 opacity-60">
          <img src="{helmet_b64}" class="w-6 h-6 object-contain" alt="Logo" />
        </div>
      </div>

      <!-- Scrollable Product Detail Content matching Figma P6 -->
      <div class="flex-1 overflow-y-auto px-6 py-2 z-10 flex flex-col items-center text-center pb-20">
        
        <!-- Product Title (Gothic / Vintage) -->
        <h2 id="detailTitle" class="vintage-shaded text-lg font-bold text-gray-900 tracking-wider mb-2 mt-1">KTM POWER 250CC</h2>

        <!-- Framed Product Box with Soft Shadow -->
        <div class="w-full max-w-[290px] h-48 bg-white rounded-2xl border-2 border-gray-800/80 shadow-xl flex items-center justify-center p-3 mb-3 overflow-hidden">
          <img id="detailImage" src="" class="max-h-full max-w-full object-contain hover:scale-105 transition-transform duration-300" alt="Producto Detalle" />
        </div>

        <!-- Specifications Centered in Uppercase -->
        <div id="detailSpecs" class="space-y-1.5 my-2">
          <!-- Dynamically filled with uppercase specs -->
        </div>

        <!-- Colores Disponibles Section with Beautiful Swatches -->
        <h4 class="vintage-shaded text-xs font-bold text-gray-900 tracking-widest uppercase mt-3 mb-1">COLORES DISPONIBLES</h4>
        <p id="selectedColorLabel" class="text-[11px] font-semibold text-gray-700 mb-2 italic">Color: Naranja Racing</p>
        
        <!-- Interactive Color Swatches Container -->
        <div id="detailColorsContainer" class="flex items-center justify-center space-x-4 mb-3">
          <!-- Dynamically injected color swatches -->
        </div>

        <!-- Price Display -->
        <div id="detailPrice" class="vintage-shaded text-base font-bold text-gray-900 mb-4 mt-0.5">$4,800 USD</div>

        <!-- Agregar al Carrito Button (Grey Pill matching Figma) -->
        <button 
          onclick="addProductToCartFromDetail()" 
          class="w-full max-w-[270px] py-3.5 rounded-full bg-[#cccccc] hover:bg-[#b5b5b5] active:scale-95 text-gray-900 font-serif font-bold text-xs tracking-widest shadow-md transition-all cursor-pointer flex items-center justify-center space-x-2"
        >
          <svg class="w-4 h-4" fill="none" stroke="currentColor" stroke-width="2.2" viewBox="0 0 24 24"><path stroke-linecap="round" stroke-linejoin="round" d="M12 4v16m8-8H4"/></svg>
          <span>AGREGAR AL CARRITO</span>
        </button>

      </div>

      <!-- Bottom Navigation Bar -->
      <div class="h-16 bg-white border-t border-gray-100 flex items-center justify-around px-8 z-30 shadow-2xl shrink-0">
        <button onclick="showToast('Ruta: Perfil 👤')" class="flex flex-col items-center justify-center p-2 text-gray-400 hover:text-black transition-all cursor-pointer">
          <svg class="w-7 h-7" fill="currentColor" viewBox="0 0 24 24"><path d="M12 12c2.21 0 4-1.79 4-4s-1.79-4-4-4-4 1.79-4 4 1.79 4 4 4zm0 2c-2.67 0-8 1.34-8 4v2h16v-2c0-2.66-5.33-4-8-4z"/></svg>
        </button>
        <button onclick="goToHome()" class="flex flex-col items-center justify-center p-2 text-black transition-all scale-110 cursor-pointer">
          <svg class="w-8 h-8" fill="currentColor" viewBox="0 0 24 24"><path d="M10 20v-6h4v6h5v-8h3L12 3 2 12h3v8z"/></svg>
        </button>
        <button onclick="goToBranchesSelection()" class="flex flex-col items-center justify-center p-2 text-gray-400 hover:text-black transition-all cursor-pointer">
          <svg class="w-7 h-7" fill="currentColor" viewBox="0 0 24 24"><path d="M3 18h18v-2H3v2zm0-5h18v-2H3v2zm0-7v2h18V6H3z"/></svg>
        </button>
      </div>

    </div>

    <!-- ============================================================== -->
    <!-- PANTALLA 8: VENTANA DE CARRITO, FACTURACIÓN Y PAGO -->
    <!-- ============================================================== -->
    <div id="screenCart" class="hidden flex flex-col h-full w-full relative bg-[#f8f9fa] overflow-hidden">
      <img src="{helmet_b64}" class="helmet-catalog-watermark" alt="Casco Watermark" />

      <!-- Top Header Navigation Bar -->
      <div class="flex items-center justify-between px-5 pt-4 pb-2 z-10 bg-white shadow-xs border-b border-gray-100 shrink-0">
        <div class="flex items-center space-x-2">
          <button onclick="goBackFromCart()" class="text-gray-900 font-bold p-1 hover:opacity-70 active:scale-90 transition-all cursor-pointer">
            <svg class="w-5 h-5" fill="none" stroke="currentColor" stroke-width="2.5" viewBox="0 0 24 24"><path stroke-linecap="round" stroke-linejoin="round" d="M15 19l-7-7 7-7"/></svg>
          </button>
          <h2 class="vintage-shaded text-lg font-bold text-gray-900">Carrito de Compras</h2>
        </div>
        <div class="flex items-center space-x-1.5 opacity-60">
          <img src="{helmet_b64}" class="w-6 h-6 object-contain" alt="Logo" />
        </div>
      </div>

      <!-- Scrollable Cart Content -->
      <div class="flex-1 overflow-y-auto px-4 py-3 z-10 space-y-3 pb-20">
        
        <!-- 1. Dynamic Multi-Product Items Container -->
        <div id="cartItemsContainer" class="space-y-2.5">
          <!-- Multi-Product Cards rendered via JavaScript -->
        </div>

        <!-- 2. Datos de Facturación y Envío Card -->
        <div class="bg-white rounded-2xl p-4 shadow-md border border-gray-100 space-y-2.5">
          <div class="flex items-center justify-between">
            <h3 class="vintage-shaded text-xs font-bold text-gray-900 tracking-wide">Datos de Facturación y Contacto</h3>
            <span class="text-[10px] bg-green-100 text-green-800 font-bold px-2 py-0.5 rounded-full">Oficial</span>
          </div>

          <input 
            type="text" 
            id="billingName"
            value="Marcelo Biker"
            placeholder="Nombre Completo o Razón Social" 
            class="w-full h-9 px-3.5 rounded-xl border border-gray-200 text-xs text-gray-800 placeholder-gray-400 focus:outline-none focus:ring-2 focus:ring-black"
          />

          <input 
            type="text" 
            id="billingNit"
            value="8472910"
            placeholder="NIT o Carnet de Identidad (CI)" 
            class="w-full h-9 px-3.5 rounded-xl border border-gray-200 text-xs text-gray-800 placeholder-gray-400 focus:outline-none focus:ring-2 focus:ring-black"
          />

          <input 
            type="tel" 
            id="billingPhone"
            value="+591 76543210"
            placeholder="Teléfono / WhatsApp de contacto" 
            class="w-full h-9 px-3.5 rounded-xl border border-gray-200 text-xs text-gray-800 placeholder-gray-400 focus:outline-none focus:ring-2 focus:ring-black"
          />

          <input 
            type="text" 
            id="billingAddress"
            placeholder="Dirección o Referencia de entrega" 
            value="Zona Sur, Av. Principal"
            class="w-full h-9 px-3.5 rounded-xl border border-gray-200 text-xs text-gray-800 placeholder-gray-400 focus:outline-none focus:ring-2 focus:ring-black"
          />
        </div>

        <!-- 3. Métodos de Pago Card -->
        <div class="bg-white rounded-2xl p-4 shadow-md border border-gray-100 space-y-2">
          <h3 class="vintage-shaded text-xs font-bold text-gray-900 tracking-wide mb-2">Método de Pago</h3>
          
          <label class="flex items-center space-x-2.5 p-2 rounded-xl border border-gray-200 hover:border-black cursor-pointer transition-all bg-gray-50/50">
            <input type="radio" name="payMethod" value="QR Simple" checked class="accent-black cursor-pointer" />
            <span class="text-xs font-semibold text-gray-800">📱 Pago Fácil QR Simple (Banco Nacional)</span>
          </label>

          <label class="flex items-center space-x-2.5 p-2 rounded-xl border border-gray-200 hover:border-black cursor-pointer transition-all bg-gray-50/50">
            <input type="radio" name="payMethod" value="Tarjeta" class="accent-black cursor-pointer" />
            <span class="text-xs font-semibold text-gray-800">💳 Tarjeta de Débito / Crédito</span>
          </label>

          <label class="flex items-center space-x-2.5 p-2 rounded-xl border border-gray-200 hover:border-black cursor-pointer transition-all bg-gray-50/50">
            <input type="radio" name="payMethod" value="Contra Entrega" class="accent-black cursor-pointer" />
            <span class="text-xs font-semibold text-gray-800">💵 Pago Contra Entrega en Sucursal</span>
          </label>
        </div>

        <!-- 4. Desglose de Costos Card -->
        <div class="bg-white rounded-2xl p-4 shadow-md border border-gray-100 space-y-1.5">
          <div class="flex justify-between text-xs text-gray-600">
            <span>Subtotal (<span id="cartItemsCountText">0</span> artículos)</span>
            <span id="cartSubtotalUSD" class="font-bold text-gray-900">$0 USD</span>
          </div>
          <div class="flex justify-between text-xs text-gray-600">
            <span>Envío / Retiro en Sucursal</span>
            <span class="font-bold text-green-600">GRATIS</span>
          </div>
          <div class="border-t border-gray-200 my-2 pt-1.5 flex justify-between items-center">
            <span class="font-serif font-bold text-sm text-gray-900">Total a Pagar</span>
            <div class="text-right">
              <span id="cartTotalUSD" class="vintage-shaded text-base font-bold text-amber-700 block">$0 USD</span>
              <span id="cartTotalBs" class="text-[11px] text-gray-600 font-semibold">(0 Bs.)</span>
            </div>
          </div>
        </div>

        <!-- Botón Elegir Sucursal -->
        <button 
          id="btnGoToBranches"
          onclick="goToBranchesSelection()" 
          class="w-full py-3.5 rounded-full bg-[#111111] hover:bg-black active:scale-95 text-white font-serif font-bold text-xs tracking-widest shadow-xl transition-all mt-1 cursor-pointer flex items-center justify-center space-x-2"
        >
          <span>ELEGIR SUCURSAL DE RETIRO</span>
          <svg class="w-4 h-4" fill="none" stroke="currentColor" stroke-width="2.5" viewBox="0 0 24 24"><path stroke-linecap="round" stroke-linejoin="round" d="M14 5l7 7m0 0l-7 7m7-7H3"/></svg>
        </button>

      </div>

      <!-- Bottom Navigation Bar -->
      <div class="h-16 bg-white border-t border-gray-100 flex items-center justify-around px-8 z-30 shadow-2xl shrink-0">
        <button onclick="showToast('Ruta: Perfil 👤')" class="flex flex-col items-center justify-center p-2 text-gray-400 hover:text-black transition-all cursor-pointer">
          <svg class="w-7 h-7" fill="currentColor" viewBox="0 0 24 24"><path d="M12 12c2.21 0 4-1.79 4-4s-1.79-4-4-4-4 1.79-4 4 1.79 4 4 4zm0 2c-2.67 0-8 1.34-8 4v2h16v-2c0-2.66-5.33-4-8-4z"/></svg>
        </button>
        <button onclick="goToHome()" class="flex flex-col items-center justify-center p-2 text-black transition-all scale-110 cursor-pointer">
          <svg class="w-8 h-8" fill="currentColor" viewBox="0 0 24 24"><path d="M10 20v-6h4v6h5v-8h3L12 3 2 12h3v8z"/></svg>
        </button>
        <button onclick="goToBranchesSelection()" class="flex flex-col items-center justify-center p-2 text-gray-400 hover:text-black transition-all cursor-pointer">
          <svg class="w-7 h-7" fill="currentColor" viewBox="0 0 24 24"><path d="M3 18h18v-2H3v2zm0-5h18v-2H3v2zm0-7v2h18V6H3z"/></svg>
        </button>
      </div>

    </div>

    <!-- ============================================================== -->
    <!-- PANTALLA 9: SELECTOR DE SUCURSALES (FIGMA P7) -->
    <!-- ============================================================== -->
    <div id="screenBranches" class="hidden flex flex-col justify-between h-full w-full relative bg-[#f8f9fa] p-6 overflow-hidden">
      <img src="{bg_b64}" class="bg-watermark" alt="Fondo Huellas" />
      <img src="{helmet_b64}" class="helmet-watermark" alt="Casco Watermark" />

      <!-- Top Header -->
      <div class="flex items-center justify-between w-full pt-1 z-10">
        <button onclick="goToCartScreen()" class="p-2 -ml-2 text-gray-900 hover:opacity-70 active:scale-90 transition-all cursor-pointer">
          <svg class="w-6 h-6" fill="none" stroke="currentColor" stroke-width="2.5" viewBox="0 0 24 24"><path stroke-linecap="round" stroke-linejoin="round" d="M10 19l-7-7m0 0l7-7m-7 7h18"/></svg>
        </button>
        <div class="flex flex-col items-center">
          <img src="{helmet_b64}" class="w-8 h-8 object-contain" alt="Logo" />
        </div>
      </div>

      <!-- Title: DONDE RECOJERAS TU ADQUISICION -->
      <div class="text-center z-10 my-2">
        <h2 class="vintage-shaded text-base font-bold text-gray-900 tracking-wider leading-snug">
          DONDE RECOJERAS TU ADQUISICION
        </h2>
      </div>

      <!-- 3 City Buttons Container -->
      <div class="flex-1 flex flex-col justify-center space-y-4 z-10 px-2 my-2">
        
        <!-- 1. La Paz -->
        <button onclick="selectBranch('La Paz')" class="relative w-full h-24 rounded-3xl overflow-hidden shadow-md group transform active:scale-95 transition-all cursor-pointer border border-gray-200">
          <img src="{map_lapaz_b64}" class="absolute inset-0 w-full h-full object-cover group-hover:scale-105 transition-transform duration-500" alt="La Paz Map" />
          <div class="absolute inset-0 bg-white/50 backdrop-blur-[0.5px] flex items-center justify-center group-hover:bg-white/40 transition-colors">
            <span class="vintage-shaded text-2xl font-bold text-gray-900 tracking-wide font-serif">La paz</span>
          </div>
        </button>

        <!-- 2. El Alto -->
        <button onclick="selectBranch('El Alto')" class="relative w-full h-24 rounded-3xl overflow-hidden shadow-md group transform active:scale-95 transition-all cursor-pointer border border-gray-200">
          <img src="{map_elalto_b64}" class="absolute inset-0 w-full h-full object-cover group-hover:scale-105 transition-transform duration-500" alt="El Alto Map" />
          <div class="absolute inset-0 bg-white/50 backdrop-blur-[0.5px] flex items-center justify-center group-hover:bg-white/40 transition-colors">
            <span class="vintage-shaded text-2xl font-bold text-gray-900 tracking-wide font-serif">El Alto</span>
          </div>
        </button>

        <!-- 3. Cochabamba -->
        <button onclick="selectBranch('Cochabamba')" class="relative w-full h-24 rounded-3xl overflow-hidden shadow-md group transform active:scale-95 transition-all cursor-pointer border border-gray-200">
          <img src="{map_cochabamba_b64}" class="absolute inset-0 w-full h-full object-cover group-hover:scale-105 transition-transform duration-500" alt="Cochabamba Map" />
          <div class="absolute inset-0 bg-white/50 backdrop-blur-[0.5px] flex items-center justify-center group-hover:bg-white/40 transition-colors">
            <span class="vintage-shaded text-2xl font-bold text-gray-900 tracking-wide font-serif">Cochabamba</span>
          </div>
        </button>
      </div>

      <!-- Footer Note -->
      <div class="text-center z-10 mb-2">
        <p class="text-xs font-serif font-bold text-gray-800 leading-tight">
          Tu pedido sera enviado<br />a la sucursal de la ciudad<br />seleccionada
        </p>
      </div>
    </div>

    <!-- ============================================================== -->
    <!-- PANTALLA 10: VISTA DE MAPA Y SUCURSAL (FIGMA P6) -->
    <!-- ============================================================== -->
    <div id="screenBranchMap" class="hidden flex flex-col justify-between h-full w-full relative bg-white overflow-hidden">
      <img src="{bg_b64}" class="bg-watermark" alt="Fondo Huellas" />

      <!-- Top Header Bar -->
      <div class="flex items-center justify-between px-5 pt-4 pb-2 z-10 bg-white/95 backdrop-blur-xs border-b border-gray-100 shrink-0">
        <button onclick="goToBranchesSelection()" class="text-gray-900 font-bold p-1 hover:opacity-70 active:scale-90 transition-all cursor-pointer">
          <svg class="w-6 h-6" fill="none" stroke="currentColor" stroke-width="2.5" viewBox="0 0 24 24"><path stroke-linecap="round" stroke-linejoin="round" d="M15 19l-7-7 7-7"/></svg>
        </button>
        <h2 id="branchMapTitle" class="vintage-shaded text-xl font-bold text-gray-900">La paz</h2>
        <div class="w-6"></div>
      </div>

      <!-- Google Maps Interactive Map Container with Rounded Frame -->
      <div class="flex-1 px-4 py-2 z-10 relative overflow-hidden flex flex-col justify-between">
        <div class="relative w-full h-full rounded-[28px] overflow-hidden shadow-lg border border-gray-200 bg-gray-100 flex flex-col justify-between">
          
          <!-- Live Interactive Google Maps iframe -->
          <iframe 
            id="googleMapIframe"
            src="https://maps.google.com/maps?q=-16.539652,-68.086808&hl=es&z=15&output=embed" 
            class="w-full h-full border-0 absolute inset-0 z-0" 
            loading="lazy" 
            allowfullscreen>
          </iframe>

          <!-- Place Card Popup Overlay (bottom of map) -->
          <div class="relative z-10 m-3 mt-auto bg-white/95 backdrop-blur-md rounded-2xl p-2.5 shadow-xl border border-gray-200 flex items-center space-x-2.5">
            <img src="{store_b64}" class="w-13 h-13 rounded-xl object-cover shadow-xs shrink-0" alt="Tienda Sucursal" />
            <div class="flex-1 min-w-0">
              <h4 id="branchPlaceCode" class="text-xs font-bold text-gray-900 truncate">FW67+66F La Paz</h4>
              <p id="branchPlaceAddress" class="text-[10px] text-gray-600 truncate">Av. Rafael Pabón, Megacenter Irpavi</p>
              <div class="flex items-center space-x-2 mt-1">
                <span class="text-[9px] bg-green-100 text-green-800 font-bold px-1.5 py-0.5 rounded-full">Abierto 09:00 - 20:00</span>
                <span class="text-[9px] text-gray-500 font-medium">Biker Shop Oficial</span>
              </div>
            </div>
            <!-- Directions Action Icon -->
            <button onclick="showToast('Ruta en Google Maps generada 📍')" class="w-9 h-9 rounded-full bg-[#00887A] flex items-center justify-center text-white shadow hover:opacity-90 active:scale-90 transition-all shrink-0 cursor-pointer" title="Cómo llegar">
              <svg class="w-4 h-4 fill-current" viewBox="0 0 24 24"><path d="M21.71 11.29l-9-9a1 1 0 0 0-1.42 0l-9 9a1 1 0 0 0 0 1.42l9 9a1 1 0 0 0 1.42 0l9-9a1 1 0 0 0 0-1.42zM14 14.5V12h-4v3H8v-4a1 1 0 0 1 1-1h5V7.5l3.5 3.5-3.5 3.5z"/></svg>
            </button>
          </div>

        </div>
      </div>

      <!-- Action Button: REALIZAR COMPRA ➔ -->
      <div class="px-6 py-2 z-10 shrink-0">
        <button 
          onclick="confirmRealizarCompra()" 
          class="w-full py-3 flex items-center justify-center space-x-3 text-gray-900 hover:text-black font-serif font-black text-sm tracking-widest active:scale-95 transition-all cursor-pointer"
        >
          <span>REALIZAR COMPRA</span>
          <svg class="w-6 h-6" fill="none" stroke="currentColor" stroke-width="2.5" viewBox="0 0 24 24"><path stroke-linecap="round" stroke-linejoin="round" d="M14 5l7 7m0 0l-7 7m7-7H3"/></svg>
        </button>
      </div>

      <!-- Bottom Navigation Bar -->
      <div class="h-14 bg-white border-t border-gray-100 flex items-center justify-around px-8 z-30 shadow-2xl shrink-0">
        <button onclick="showToast('Ruta: Perfil 👤')" class="flex flex-col items-center justify-center p-2 text-gray-400 hover:text-black transition-all cursor-pointer">
          <svg class="w-6 h-6" fill="currentColor" viewBox="0 0 24 24"><path d="M12 12c2.21 0 4-1.79 4-4s-1.79-4-4-4-4 1.79-4 4 1.79 4 4 4zm0 2c-2.67 0-8 1.34-8 4v2h16v-2c0-2.66-5.33-4-8-4z"/></svg>
        </button>
        <button onclick="goToHome()" class="flex flex-col items-center justify-center p-2 text-black transition-all scale-110 cursor-pointer">
          <svg class="w-7 h-7" fill="currentColor" viewBox="0 0 24 24"><path d="M10 20v-6h4v6h5v-8h3L12 3 2 12h3v8z"/></svg>
        </button>
        <button onclick="goToBranchesSelection()" class="flex flex-col items-center justify-center p-2 text-gray-400 hover:text-black transition-all cursor-pointer">
          <svg class="w-6 h-6" fill="currentColor" viewBox="0 0 24 24"><path d="M3 18h18v-2H3v2zm0-5h18v-2H3v2zm0-7v2h18V6H3z"/></svg>
        </button>
      </div>

    </div>

    <!-- Toast Notification -->
    <div id="toast" class="absolute top-8 left-1/2 -translate-x-1/2 bg-gray-900 text-white text-xs font-bold px-4 py-2 rounded-full shadow-2xl transition-all duration-300 opacity-0 pointer-events-none z-50">
      <span id="toastMsg">Mensaje</span>
    </div>

  </div>

  <script>
    const motosList = {motos_json};
    const helmetsList = {helmets_json};
    const gearList = {gear_json};

    // Global Shopping Cart (Array of accumulated products - starts with 0 items)
    let cart = [];

    let currentDetailItem = motosList[0];
    let currentCategory = 'motos';
    let currentSelectedColor = 'Naranja KTM Racing';
    let selectedBranchCity = 'La Paz';

    function updateFloatingCartBadge() {{
      const count = cart.reduce((sum, item) => sum + item.qty, 0);
      const badge = document.getElementById('floatingCartBadge');
      const btn = document.getElementById('floatingCartBtn');
      
      if (badge) {{
        badge.innerText = count;
      }}
      if (btn) {{
        btn.classList.add('animate-cart-bounce');
        setTimeout(() => btn.classList.remove('animate-cart-bounce'), 400);
      }}
    }}

    function showFloatingCart(show) {{
      const container = document.getElementById('floatingCartContainer');
      if (!container) return;
      if (show) {{
        container.classList.remove('hidden');
        updateFloatingCartBadge();
      }} else {{
        container.classList.add('hidden');
      }}
    }}

    function handleHomeSearch(query) {{
      const q = (query || '').trim().toLowerCase();
      const clearBtn = document.getElementById('btnHomeSearchClear');
      const catsContainer = document.getElementById('homeCategoriesContainer');
      const resultsContainer = document.getElementById('homeSearchResultsContainer');
      const resultsList = document.getElementById('homeSearchResultsList');
      const searchCount = document.getElementById('homeSearchCount');

      if (!q) {{
        if (clearBtn) clearBtn.classList.add('hidden');
        if (resultsContainer) resultsContainer.classList.add('hidden');
        if (catsContainer) catsContainer.classList.remove('hidden');
        return;
      }}

      if (clearBtn) clearBtn.classList.remove('hidden');
      if (catsContainer) catsContainer.classList.add('hidden');
      if (resultsContainer) resultsContainer.classList.remove('hidden');

      // Aggregate all products across the 3 categories
      const allProducts = [
        ...motosList.map(m => ({{ ...m, catKey: 'motos', catLabel: '🏍️ Motocicleta' }})),
        ...helmetsList.map(h => ({{ ...h, catKey: 'cascos', catLabel: '🪖 Casco Racing' }})),
        ...gearList.map(g => ({{ ...g, catKey: 'indumentaria', catLabel: '🧥 Indumentaria' }}))
      ];

      const matches = allProducts.filter(p => {{
        const nameMatch = p.name.toLowerCase().includes(q);
        const specMatch = p.specs && p.specs.some(s => s.toLowerCase().includes(q));
        const colorMatch = p.colors && p.colors.some(c => c.name.toLowerCase().includes(q));
        const catMatch = p.catLabel.toLowerCase().includes(q);
        return nameMatch || specMatch || colorMatch || catMatch;
      }});

      if (searchCount) searchCount.innerText = `${{matches.length}} producto(s) encontrado(s)`;
      if (!resultsList) return;
      resultsList.innerHTML = '';

      if (matches.length === 0) {{
        resultsList.innerHTML = `
          <div class=\"bg-gray-50 rounded-2xl p-6 text-center border border-gray-200 space-y-2 mt-2\">
            <span class=\"text-3xl\">🔍</span>
            <p class=\"font-serif font-bold text-gray-800 text-sm\">No encontramos productos para \"${{query}}\"</p>
            <p class=\"text-[11px] text-gray-500\">Prueba buscando: KTM, Ninja, AGV, Chaqueta, Guantes, Botas, Carbono, Enduro.</p>
          </div>
        `;
        return;
      }}

      matches.forEach(item => {{
        const card = document.createElement('div');
        card.className = 'bg-white rounded-2xl p-2.5 shadow-md border border-gray-100 flex items-center space-x-3 hover:shadow-lg transition-all active:scale-98 cursor-pointer';
        card.onclick = () => {{
          openProductDetail(item, item.catKey);
        }};

        card.innerHTML = `
          <div class=\"w-16 h-16 rounded-xl bg-gray-50 p-1 flex items-center justify-center shrink-0 border border-gray-100 overflow-hidden\">
            <img src=\"${{item.img}}\" class=\"max-h-full max-w-full object-contain hover:scale-105 transition-transform\" alt=\"${{item.name}}\" />
          </div>
          <div class=\"flex-1 min-w-0\">
            <span class=\"inline-block text-[9px] font-bold px-1.5 py-0.2 rounded-full bg-gray-100 text-gray-700 border border-gray-200 mb-0.5\">${{item.catLabel}}</span>
            <h4 class=\"vintage-shaded text-xs font-bold text-gray-900 truncate leading-tight\">${{item.name}}</h4>
            <div class=\"text-amber-700 font-bold text-xs mt-0.5\">${{item.price}}</div>
          </div>
          <div class=\"text-gray-400 font-bold text-base px-2\">➔</div>
        `;
        resultsList.appendChild(card);
      }});
    }}

    function clearHomeSearch() {{
      const input = document.getElementById('homeSearchInput');
      if (input) input.value = '';
      handleHomeSearch('');
    }}

    function renderMotosGrid() {{
      const grid = document.getElementById('motosGrid');
      grid.innerHTML = '';

      motosList.forEach((m) => {{
        const card = document.createElement('div');
        card.className = 'bg-white/90 backdrop-blur-xs border border-gray-100 rounded-2xl p-2.5 shadow-md flex flex-col items-center text-center hover:shadow-lg transition-all active:scale-98 cursor-pointer';
        card.onclick = () => openProductDetail(m, 'motos');

        let specsHtml = '';
        m.specs.forEach(s => {{
          specsHtml += `<p class=\"text-[9.5px] text-gray-600 font-medium leading-tight\">• ${{s}}</p>`;
        }});

        card.innerHTML = `
          <div class=\"w-full h-24 flex items-center justify-center mb-1 overflow-hidden\">
            <img src=\"${{m.img}}\" class=\"max-h-full max-w-full object-contain hover:scale-105 transition-transform duration-300\" alt=\"${{m.name}}\" />
          </div>
          <h4 class=\"vintage-shaded text-[11.5px] font-bold text-gray-900 leading-tight mb-1\">${{m.name}}</h4>
          <span class=\"gothic-arch text-[11px] font-bold text-gray-800 -mt-0.5 mb-1\">caracteristicas</span>
          <div class=\"space-y-0.5 my-1\">
            ${{specsHtml}}
          </div>
          <span class=\"text-[11px] font-bold text-gray-900 mt-1 bg-gray-100 px-2 py-0.5 rounded-full\">${{m.price}}</span>
        `;
        grid.appendChild(card);
      }});
    }}

    function renderCascosGrid() {{
      const grid = document.getElementById('cascosGrid');
      grid.innerHTML = '';

      helmetsList.forEach((h) => {{
        const card = document.createElement('div');
        card.className = 'bg-white/90 backdrop-blur-xs border border-gray-100 rounded-2xl p-2.5 shadow-md flex flex-col items-center text-center hover:shadow-lg transition-all active:scale-98 cursor-pointer';
        card.onclick = () => openProductDetail(h, 'cascos');

        let specsHtml = '';
        h.specs.forEach(s => {{
          specsHtml += `<p class=\"text-[9.5px] text-gray-600 font-medium leading-tight\">• ${{s}}</p>`;
        }});

        card.innerHTML = `
          <div class=\"w-full h-24 flex items-center justify-center mb-1 overflow-hidden rounded-xl bg-gray-50 p-1\">
            <img src=\"${{h.img}}\" class=\"max-h-full max-w-full object-cover rounded-lg hover:scale-105 transition-transform duration-300\" alt=\"${{h.name}}\" />
          </div>
          <h4 class=\"vintage-shaded text-[11.5px] font-bold text-gray-900 leading-tight mb-1\">${{h.name}}</h4>
          <span class=\"gothic-arch text-[11px] font-bold text-gray-800 -mt-0.5 mb-1\">especificaciones</span>
          <div class=\"space-y-0.5 my-1\">
            ${{specsHtml}}
          </div>
          <span class=\"text-[11px] font-bold text-amber-700 mt-1 bg-amber-50 border border-amber-200 px-2 py-0.5 rounded-full\">${{h.price}}</span>
        `;
        grid.appendChild(card);
      }});
    }}

    function renderGearGrid() {{
      const grid = document.getElementById('indumGrid');
      grid.innerHTML = '';

      gearList.forEach((g) => {{
        const card = document.createElement('div');
        card.className = 'bg-white/90 backdrop-blur-xs border border-gray-100 rounded-2xl p-2.5 shadow-md flex flex-col items-center text-center hover:shadow-lg transition-all active:scale-98 cursor-pointer';
        card.onclick = () => openProductDetail(g, 'indumentaria');

        let specsHtml = '';
        g.specs.forEach(s => {{
          specsHtml += `<p class=\"text-[9.5px] text-gray-600 font-medium leading-tight\">• ${{s}}</p>`;
        }});

        card.innerHTML = `
          <div class=\"w-full h-24 flex items-center justify-center mb-1 overflow-hidden rounded-xl bg-gray-50 p-1\">
            <img src=\"${{g.img}}\" class=\"max-h-full max-w-full object-cover rounded-lg hover:scale-105 transition-transform duration-300\" alt=\"${{g.name}}\" />
          </div>
          <h4 class=\"vintage-shaded text-[11.5px] font-bold text-gray-900 leading-tight mb-1\">${{g.name}}</h4>
          <span class=\"gothic-arch text-[11px] font-bold text-gray-800 -mt-0.5 mb-1\">especificaciones</span>
          <div class=\"space-y-0.5 my-1\">
            ${{specsHtml}}
          </div>
          <span class=\"text-[11px] font-bold text-amber-700 mt-1 bg-amber-50 border border-amber-200 px-2 py-0.5 rounded-full\">${{g.price}}</span>
        `;
        grid.appendChild(card);
      }});
    }}

    function openProductDetail(item, category) {{
      currentDetailItem = item;
      currentCategory = category;

      document.getElementById('detailTitle').innerText = item.name.toUpperCase();
      document.getElementById('detailImage').src = item.img;
      document.getElementById('detailPrice').innerText = item.price;

      // Specs
      const specsContainer = document.getElementById('detailSpecs');
      specsContainer.innerHTML = '';
      item.specs.forEach(s => {{
        const p = document.createElement('p');
        p.className = 'text-xs font-bold text-gray-800 tracking-wider font-serif';
        p.innerText = s.toUpperCase().replace(/^•\\s*/, '');
        specsContainer.appendChild(p);
      }});

      // Color Swatches
      const colorsContainer = document.getElementById('detailColorsContainer');
      colorsContainer.innerHTML = '';

      const colors = item.colors || [
        {{"name": "Negro Clásico", "hex": "#1A1A1A"}},
        {{"name": "Gris Titanio", "hex": "#4A4A4A"}},
        {{"name": "Rojo Racing", "hex": "#D32F2F"}}
      ];

      currentSelectedColor = colors[0].name;
      document.getElementById('selectedColorLabel').innerText = 'Color: ' + currentSelectedColor;

      colors.forEach((c, idx) => {{
        const btn = document.createElement('button');
        btn.className = `w-10 h-10 rounded-full shadow-md transform hover:scale-110 active:scale-95 transition-all color-swatch border-2 border-white ${{idx === 0 ? 'ring-2 ring-offset-2 ring-black scale-105' : 'ring-0'}} cursor-pointer`;
        btn.style.backgroundColor = c.hex;
        btn.title = c.name;
        btn.onclick = () => {{
          document.querySelectorAll('.color-swatch').forEach(s => s.classList.remove('ring-2', 'ring-offset-2', 'ring-black', 'scale-105'));
          btn.classList.add('ring-2', 'ring-offset-2', 'ring-black', 'scale-105');
          currentSelectedColor = c.name;
          document.getElementById('selectedColorLabel').innerText = 'Color: ' + currentSelectedColor;
          showToast('Color: ' + c.name + ' 🎨');
        }};
        colorsContainer.appendChild(btn);
      }});

      hideAllScreens();
      document.getElementById('screenProductDetail').classList.remove('hidden');
      showFloatingCart(true);
      showToast('Vista de Detalle: ' + item.name + ' 🔍');
    }}

    // Multi-Product Add To Cart
    function addProductToCartFromDetail() {{
      if (!currentDetailItem) return;

      const existingIndex = cart.findIndex(
        it => it.name === currentDetailItem.name && it.color === currentSelectedColor
      );

      if (existingIndex > -1) {{
        cart[existingIndex].qty += 1;
      }} else {{
        cart.push({{
          id: currentDetailItem.id || 'item_' + Date.now(),
          name: currentDetailItem.name,
          category: currentDetailItem.category || 'Producto',
          img: currentDetailItem.img,
          price_num: currentDetailItem.price_num || 4800,
          color: currentSelectedColor,
          qty: 1
        }});
      }}

      updateFloatingCartBadge();
      showToast('¡' + currentDetailItem.name + ' agregado al carrito! 🛒');
    }}

    function renderCartItems() {{
      const container = document.getElementById('cartItemsContainer');
      container.innerHTML = '';

      if (cart.length === 0) {{
        container.innerHTML = `
          <div class=\"bg-white rounded-2xl p-6 shadow-md border border-gray-100 flex flex-col items-center justify-center text-center space-y-2\">
            <span class=\"text-4xl\">🛒</span>
            <p class=\"font-serif font-bold text-gray-800 text-sm\">Tu carrito está vacío</p>
            <p class=\"text-xs text-gray-500\">Explora el catálogo y agrega motos, cascos o indumentaria.</p>
            <button onclick=\"goToHome()\" class=\"mt-2 px-5 py-2 rounded-full bg-gray-900 text-white font-bold text-xs hover:bg-black active:scale-95 transition-all cursor-pointer\">
              Ver Catálogo 🏍️
            </button>
          </div>
        `;
        updateCartTotals();
        return;
      }}

      cart.forEach((item, index) => {{
        const itemTotalUSD = item.price_num * item.qty;
        const itemTotalBs = Math.round(itemTotalUSD * 6.96);

        const card = document.createElement('div');
        card.className = 'bg-white rounded-2xl p-3.5 shadow-md border border-gray-100 flex items-center space-x-3 transition-all';
        card.innerHTML = `
          <div class=\"w-18 h-18 rounded-xl bg-gray-50 p-1 flex items-center justify-center shrink-0 border border-gray-100 overflow-hidden\">
            <img src=\"${{item.img}}\" class=\"max-h-full max-w-full object-contain\" alt=\"${{item.name}}\" />
          </div>

          <div class=\"flex-1 min-w-0\">
            <h4 class=\"vintage-shaded text-xs font-bold text-gray-900 truncate leading-tight\">${{item.name}}</h4>
            <span class=\"inline-block text-[10px] text-gray-500 font-medium mb-0.5\">${{item.color}}</span>
            <div class=\"text-amber-700 font-bold text-xs\">
              $${{itemTotalUSD.toLocaleString('en-US')}} USD
              <span class=\"text-[10px] text-gray-500 font-normal\">(${{itemTotalBs.toLocaleString('es-BO')}} Bs.)</span>
            </div>
          </div>

          <div class=\"flex flex-col items-center space-y-1 shrink-0\">
            <div class=\"flex items-center space-x-1.5 bg-gray-100 rounded-full px-2 py-0.5\">
              <button onclick=\"changeCartItemQty(${{index}}, -1)\" class=\"w-5 h-5 rounded-full bg-white shadow-xs font-bold text-xs flex items-center justify-center hover:bg-gray-200 active:scale-90 cursor-pointer\">-</button>
              <span class=\"text-xs font-bold px-1 text-gray-900\">${{item.qty}}</span>
              <button onclick=\"changeCartItemQty(${{index}}, 1)\" class=\"w-5 h-5 rounded-full bg-white shadow-xs font-bold text-xs flex items-center justify-center hover:bg-gray-200 active:scale-90 cursor-pointer\">+</button>
            </div>
            <button onclick=\"removeCartItem(${{index}})\" class=\"text-[10px] text-red-500 hover:text-red-700 font-semibold cursor-pointer pt-0.5\">
              Eliminar
            </button>
          </div>
        `;
        container.appendChild(card);
      }});

      updateCartTotals();
    }}

    function changeCartItemQty(index, delta) {{
      if (cart[index]) {{
        const newQty = cart[index].qty + delta;
        if (newQty <= 0) {{
          cart.splice(index, 1);
        }} else if (newQty <= 10) {{
          cart[index].qty = newQty;
        }}
        renderCartItems();
        updateFloatingCartBadge();
      }}
    }}

    function removeCartItem(index) {{
      if (cart[index]) {{
        showToast('Producto eliminado del carrito');
        cart.splice(index, 1);
        renderCartItems();
        updateFloatingCartBadge();
      }}
    }}

    function updateCartTotals() {{
      const totalCount = cart.reduce((sum, item) => sum + item.qty, 0);
      const totalUSD = cart.reduce((sum, item) => sum + (item.price_num * item.qty), 0);
      const totalBs = Math.round(totalUSD * 6.96);

      const countEl = document.getElementById('cartItemsCountText');
      const subtotalEl = document.getElementById('cartSubtotalUSD');
      const totalUsdEl = document.getElementById('cartTotalUSD');
      const totalBsEl = document.getElementById('cartTotalBs');
      const btnBranches = document.getElementById('btnGoToBranches');

      if (countEl) countEl.innerText = totalCount;
      if (subtotalEl) subtotalEl.innerText = '$' + totalUSD.toLocaleString('en-US') + ' USD';
      if (totalUsdEl) totalUsdEl.innerText = '$' + totalUSD.toLocaleString('en-US') + ' USD';
      if (totalBsEl) totalBsEl.innerText = '(' + totalBs.toLocaleString('es-BO') + ' Bs.)';

      if (btnBranches) {{
        if (cart.length === 0) {{
          btnBranches.disabled = true;
          btnBranches.classList.add('opacity-50', 'cursor-not-allowed');
        }} else {{
          btnBranches.disabled = false;
          btnBranches.classList.remove('opacity-50', 'cursor-not-allowed');
        }}
      }}
    }}

    function goToCartScreen() {{
      hideAllScreens();
      document.getElementById('screenCart').classList.remove('hidden');
      renderCartItems();
      showFloatingCart(false); // Hide while in cart screen
      showToast('Carrito de Compras (' + cart.reduce((s,i)=>s+i.qty,0) + ' items) 🛒');
    }}

    function goBackFromCart() {{
      if (currentDetailItem) {{
        hideAllScreens();
        document.getElementById('screenProductDetail').classList.remove('hidden');
        showFloatingCart(true);
      }} else {{
        goToHome();
      }}
    }}

    function goBackFromDetail() {{
      if (currentCategory === 'cascos') {{
        goToCascosCatalog();
      }} else if (currentCategory === 'indumentaria') {{
        goToIndumentariaCatalog();
      }} else {{
        goToMotosCatalog();
      }}
    }}

    function showToast(msg) {{
      const toast = document.getElementById('toast');
      document.getElementById('toastMsg').innerText = msg;
      toast.classList.remove('opacity-0', 'pointer-events-none');
      setTimeout(() => {{
        toast.classList.add('opacity-0', 'pointer-events-none');
      }}, 2500);
    }}

    function goToRegister() {{
      hideAllScreens();
      document.getElementById('screenRegister').classList.remove('hidden');
      showFloatingCart(false);
    }}

    function goToLogin() {{
      hideAllScreens();
      document.getElementById('screenLogin').classList.remove('hidden');
      showFloatingCart(false);
    }}

    function goToHome() {{
      hideAllScreens();
      document.getElementById('screenHome').classList.remove('hidden');
      showFloatingCart(true);
      showToast('¡Menú Principal Chelo Biker! 🏁');
    }}

    function goToMotosCatalog() {{
      hideAllScreens();
      document.getElementById('screenMotosCatalog').classList.remove('hidden');
      renderMotosGrid();
      showFloatingCart(true);
      showToast('Catálogo de Motos 🏍️');
    }}

    function goToCascosCatalog() {{
      hideAllScreens();
      document.getElementById('screenCascosCatalog').classList.remove('hidden');
      renderCascosGrid();
      showFloatingCart(true);
      showToast('Catálogo de Cascos 🪖');
    }}

    function goToIndumentariaCatalog() {{
      hideAllScreens();
      document.getElementById('screenIndumentariaCatalog').classList.remove('hidden');
      renderGearGrid();
      showFloatingCart(true);
      showToast('Catálogo de Indumentaria 🧥');
    }}

    function goToBranchesSelection() {{
      if (cart.length === 0) {{
        showToast('⚠️ Tu carrito está vacío');
        return;
      }}
      hideAllScreens();
      document.getElementById('screenBranches').classList.remove('hidden');
      showFloatingCart(false);
      showToast('Elige la sucursal de retiro 📍');
    }}

    function selectBranch(city) {{
      selectedBranchCity = city;
      document.getElementById('branchMapTitle').innerText = city;

      const iframe = document.getElementById('googleMapIframe');
      const codeEl = document.getElementById('branchPlaceCode');
      const addrEl = document.getElementById('branchPlaceAddress');

      if (city === 'El Alto') {{
        iframe.src = 'https://maps.google.com/maps?q=-16.537243,-68.172773&hl=es&z=15&output=embed';
        codeEl.innerText = 'Panamericana El Alto';
        addrEl.innerText = 'Av. Panamericana & 6 de Marzo, Cruce Viacha, El Alto';
      }} else if (city === 'Cochabamba') {{
        iframe.src = 'https://maps.google.com/maps?q=-17.393845,-66.157821&hl=es&z=15&output=embed';
        codeEl.innerText = 'JR3R+HCQ Jordan, Cochabamba';
        addrEl.innerText = 'Av. Heroínas #450 esq. Ayacucho, Plaza Principal 14 de Septiembre';
      }} else {{
        iframe.src = 'https://maps.google.com/maps?q=-16.539652,-68.086808&hl=es&z=15&output=embed';
        codeEl.innerText = 'FW67+66F La Paz';
        addrEl.innerText = 'Av. Rafael Pabón, Megacenter Irpavi, Zona Sur';
      }}

      hideAllScreens();
      document.getElementById('screenBranchMap').classList.remove('hidden');
      showFloatingCart(false);
      showToast('Sucursal seleccionada: ' + city + ' 🗺️');
    }}

    function confirmRealizarCompra() {{
      const name = document.getElementById('billingName').value.trim() || 'Marcelo Biker';
      const nit = document.getElementById('billingNit').value.trim() || 'S/N';
      const phone = document.getElementById('billingPhone').value.trim() || '76543210';
      const payMethodRadio = document.querySelector('input[name="payMethod"]:checked');
      const payMethod = payMethodRadio ? payMethodRadio.value : 'QR Simple';
      const orderId = 'CHELO-' + Math.floor(100000 + Math.random() * 900000);
      
      const totalUSD = cart.reduce((sum, item) => sum + (item.price_num * item.qty), 0);
      const totalBs = Math.round(totalUSD * 6.96);

      let itemsSummary = '';
      cart.forEach(item => {{
        itemsSummary += '  • ' + item.name + ' (' + item.color + ') x' + item.qty + ' = $' + (item.price_num * item.qty).toLocaleString('en-US') + ' USD\\n';
      }});

      const msg = '🎉 ¡COMPRA REALIZADA CON ÉXITO! 🎉\\n\\n' +
        '• Código de Orden: ' + orderId + '\\n' +
        '• Cliente: ' + name + '\\n' +
        '• NIT/CI: ' + nit + '\\n' +
        '• Teléfono: ' + phone + '\\n' +
        '• Sucursal de Retiro: ' + selectedBranchCity + '\\n' +
        '• Método de Pago: ' + payMethod + '\\n\\n' +
        'ARTÍCULOS ADQUIRIDOS:\\n' + itemsSummary + '\\n' +
        'TOTAL FACTURADO: $' + totalUSD.toLocaleString('en-US') + ' USD (' + totalBs.toLocaleString('es-BO') + ' Bs.)\\n\\n' +
        '¡Tu factura electrónica oficial y comprobante de entrega en sucursal han sido emitidos!';

      alert(msg);
      cart = [];
      updateFloatingCartBadge();
      goToHome();
    }}

    function hideAllScreens() {{
      document.getElementById('screenLogin').classList.add('hidden');
      document.getElementById('screenRegister').classList.add('hidden');
      document.getElementById('screenHome').classList.add('hidden');
      document.getElementById('screenMotosCatalog').classList.add('hidden');
      document.getElementById('screenCascosCatalog').classList.add('hidden');
      document.getElementById('screenIndumentariaCatalog').classList.add('hidden');
      document.getElementById('screenProductDetail').classList.add('hidden');
      document.getElementById('screenCart').classList.add('hidden');
      document.getElementById('screenBranches').classList.add('hidden');
      document.getElementById('screenBranchMap').classList.add('hidden');
    }}

    function handleLogin() {{
      const emailInput = document.getElementById('emailInput');
      const email = emailInput ? (emailInput.value.trim() || 'marcelo@chelobiker.com') : 'marcelo@chelobiker.com';

      showToast('¡Bienvenido ' + email.split('@')[0] + '! 🏍️');
      goToHome();
    }}

    function handleRegisterSubmit() {{
      const emailInput = document.getElementById('regEmailInput');
      const email = emailInput ? (emailInput.value.trim() || 'nuevo_biker@chelo.com') : 'nuevo_biker@chelo.com';

      showToast('¡Cuenta creada con éxito! 🏍️');
      goToHome();
    }}

    // Init
    updateFloatingCartBadge();
  </script>
</body>
</html>
"""

with open('preview.html', 'w', encoding='utf-8') as f:
    f.write(html_content)

brain_dir = r"C:\Users\MARCELO\.gemini\antigravity\brain\23b335a5-3f54-4962-b2c4-e228471467c9"
with open(os.path.join(brain_dir, 'chelo_biker_preview.html'), 'w', encoding='utf-8') as f:
    f.write(html_content)

print("Generated Preview with Floating Cart and Multi-Product Checkout successfully!")
