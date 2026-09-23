import os
import urllib.request

drawable_dir = r"c:\Users\MARCELO\Desktop\Chelo Biker Shop\app\src\main\res\drawable"
img_dir = r"c:\Users\MARCELO\Desktop\Chelo Biker Shop\Imagenes"

os.makedirs(drawable_dir, exist_ok=True)
os.makedirs(img_dir, exist_ok=True)

# List of high-res helmet images from Unsplash / reliable CDNs
helmets = [
    {
        "filename": "casco_agv_pista.png",
        "url": "https://images.unsplash.com/photo-1558981403-c5f9899a28bc?w=500&auto=format&fit=crop&q=80" # Racing helmet
    },
    {
        "filename": "casco_shoei_x14.png",
        "url": "https://images.unsplash.com/photo-1542282088-72c9c27ed0cd?w=500&auto=format&fit=crop&q=80" # Full face helmet
    },
    {
        "filename": "casco_bell_moto9.png",
        "url": "https://images.unsplash.com/photo-1558980394-4c7c9299fe96?w=500&auto=format&fit=crop&q=80" # Motocross helmet
    },
    {
        "filename": "casco_alpinestars_sm10.png",
        "url": "https://images.unsplash.com/photo-1579783902614-a3fb3927b675?w=500&auto=format&fit=crop&q=80" # Art helmet
    },
    {
        "filename": "casco_hjc_rpha.png",
        "url": "https://images.unsplash.com/photo-1558981420-87aa9dad1c89?w=500&auto=format&fit=crop&q=80" # Sport helmet
    },
    {
        "filename": "casco_arai_rx7v.png",
        "url": "https://images.unsplash.com/photo-1558981806-ec527fa84c39?w=500&auto=format&fit=crop&q=80" # Dark helmet
    },
    {
        "filename": "casco_fox_v3.png",
        "url": "https://images.unsplash.com/photo-1558980664-769d59546b3d?w=500&auto=format&fit=crop&q=80" # Offroad helmet
    },
    {
        "filename": "casco_ls2_thunder.png",
        "url": "https://images.unsplash.com/photo-1558981359-219d6364c9c8?w=500&auto=format&fit=crop&q=80" # Carbon helmet
    }
]

# List of high-res indumentaria/gear images
gear = [
    {
        "filename": "gear_chaqueta_cuero.png",
        "url": "https://images.unsplash.com/photo-1551028719-00167b16eac5?w=500&auto=format&fit=crop&q=80" # Leather jacket
    },
    {
        "filename": "gear_guantes_racing.png",
        "url": "https://images.unsplash.com/photo-1588850561407-ed78c282e89b?w=500&auto=format&fit=crop&q=80" # Leather gloves
    },
    {
        "filename": "gear_botas_supertech.png",
        "url": "https://images.unsplash.com/photo-1542291026-7eec264c27ff?w=500&auto=format&fit=crop&q=80" # Racing boots
    },
    {
        "filename": "gear_traje_monomono.png",
        "url": "https://images.unsplash.com/photo-1509631179647-0177331693ae?w=500&auto=format&fit=crop&q=80" # Racing suit
    },
    {
        "filename": "gear_pantalon_kevlar.png",
        "url": "https://images.unsplash.com/photo-1541099649105-f69ad21f3246?w=500&auto=format&fit=crop&q=80" # Moto jeans
    },
    {
        "filename": "gear_chaqueta_textil.png",
        "url": "https://images.unsplash.com/photo-1548883354-7622d03aca27?w=500&auto=format&fit=crop&q=80" # Touring jacket
    },
    {
        "filename": "gear_botas_adventure.png",
        "url": "https://images.unsplash.com/photo-1520639888713-7851133b1ed0?w=500&auto=format&fit=crop&q=80" # Adventure boots
    },
    {
        "filename": "gear_chaleco_airbag.png",
        "url": "https://images.unsplash.com/photo-1618354691373-d851c5c3a990?w=500&auto=format&fit=crop&q=80" # Tech vest
    }
]

headers = {'User-Agent': 'Mozilla/5.0 (Windows NT 10.0; Win64; x64)'}

all_items = helmets + gear

for item in all_items:
    dst_drawable = os.path.join(drawable_dir, item["filename"])
    dst_img = os.path.join(img_dir, item["filename"])
    print(f"Downloading {item['filename']}...")
    try:
        req = urllib.request.Request(item["url"], headers=headers)
        with urllib.request.urlopen(req, timeout=10) as response, open(dst_drawable, 'wb') as out_file:
            data = response.read()
            out_file.write(data)
            with open(dst_img, 'wb') as f2:
                f2.write(data)
        print(f"  -> Saved {item['filename']}")
    except Exception as e:
        print(f"  -> Error: {e}")

print("Done downloading gear and helmets assets!")
