import os
from PIL import Image, ImageOps, ImageDraw
import numpy as np

src_path = r'C:\Users\MARCELO\.gemini\antigravity\brain\23b335a5-3f54-4962-b2c4-e228471467c9\.user_uploaded\media_1790264015506.png'
raw_img = Image.open(src_path).convert('RGBA')

# 1. Create square master image
w, h = raw_img.size
max_dim = max(w, h)
square_img = Image.new('RGBA', (max_dim, max_dim), (255, 255, 255, 0))
offset = ((max_dim - w) // 2, (max_dim - h) // 2)
square_img.paste(raw_img, offset)
square_512 = square_img.resize((512, 512), Image.Resampling.LANCZOS)

# 2. Extract clean transparent line-art for ic_helmet_logo (used for in-app headers & watermarks)
arr = np.array(square_512)
r, g, b, a = arr[:,:,0], arr[:,:,1], arr[:,:,2], arr[:,:,3]
# Compute luminance / brightness
luminance = 0.299 * r + 0.587 * g + 0.114 * b

# Black lines have luminance < 90
# We want black lines (0,0,0) with alpha proportional to darkness
alpha_mask = np.clip((140 - luminance) * (255.0 / 140.0), 0, 255).astype(np.uint8)

transparent_helmet = np.zeros((512, 512, 4), dtype=np.uint8)
transparent_helmet[:,:,0] = 0   # R
transparent_helmet[:,:,1] = 0   # G
transparent_helmet[:,:,2] = 0   # B
transparent_helmet[:,:,3] = alpha_mask

img_transparent = Image.fromarray(transparent_helmet, 'RGBA')
img_transparent.save(r'app/src/main/res/drawable/ic_helmet_logo.png')
print("Saved drawable/ic_helmet_logo.png")

# 3. Create rich app launcher icons (Square & Round)
# Create a stylized Biker app icon with the logo
def make_app_icon(size, is_round=False):
    # Canvas
    icon = Image.new('RGBA', (size, size), (255, 255, 255, 0))
    draw = ImageDraw.Draw(icon)
    
    # Background: clean modern dark/light or the full uploaded graphic
    # Resize the uploaded raw image to fit nicely inside with padding
    pad = int(size * 0.08)
    inner_size = size - (pad * 2)
    inner_img = square_512.resize((inner_size, inner_size), Image.Resampling.LANCZOS)
    
    if is_round:
        # Circular mask
        mask = Image.new('L', (size, size), 0)
        mask_draw = ImageDraw.Draw(mask)
        mask_draw.ellipse((0, 0, size - 1, size - 1), fill=255)
        
        # Base white circle with subtle dark border
        bg = Image.new('RGBA', (size, size), (248, 248, 248, 255))
        bg_draw = ImageDraw.Draw(bg)
        bg.paste(inner_img, (pad, pad), inner_img)
        bg_draw.ellipse((0, 0, size - 1, size - 1), outline=(220, 220, 220, 255), width=max(1, size // 48))
        
        icon.paste(bg, (0, 0), mask)
    else:
        # Rounded square mask
        corner_radius = int(size * 0.22)
        mask = Image.new('L', (size, size), 0)
        mask_draw = ImageDraw.Draw(mask)
        mask_draw.rounded_rectangle((0, 0, size - 1, size - 1), radius=corner_radius, fill=255)
        
        bg = Image.new('RGBA', (size, size), (248, 248, 248, 255))
        bg_draw = ImageDraw.Draw(bg)
        bg.paste(inner_img, (pad, pad), inner_img)
        bg_draw.rounded_rectangle((0, 0, size - 1, size - 1), radius=corner_radius, outline=(220, 220, 220, 255), width=max(1, size // 48))
        
        icon.paste(bg, (0, 0), mask)
        
    return icon

densities = {
    'mipmap-mdpi': 48,
    'mipmap-hdpi': 72,
    'mipmap-xhdpi': 96,
    'mipmap-xxhdpi': 144,
    'mipmap-xxxhdpi': 192,
}

res_dir = r'app/src/main/res'
for folder, sz in densities.items():
    folder_path = os.path.join(res_dir, folder)
    os.makedirs(folder_path, exist_ok=True)
    
    # Save standard icon (.webp and .png)
    sq_icon = make_app_icon(sz, is_round=False)
    sq_icon.save(os.path.join(folder_path, 'ic_launcher.png'))
    sq_icon.save(os.path.join(folder_path, 'ic_launcher.webp'))
    
    # Save round icon (.webp and .png)
    rd_icon = make_app_icon(sz, is_round=True)
    rd_icon.save(os.path.join(folder_path, 'ic_launcher_round.png'))
    rd_icon.save(os.path.join(folder_path, 'ic_launcher_round.webp'))
    
    print(f"Generated icons for {folder} ({sz}x{sz})")

# 4. Generate adaptive icon foreground
fg_img = Image.new('RGBA', (432, 432), (0, 0, 0, 0))
inner_fg = square_512.resize((260, 260), Image.Resampling.LANCZOS)
fg_img.paste(inner_fg, ((432 - 260) // 2, (432 - 260) // 2), inner_fg)
fg_img.save(os.path.join(res_dir, 'drawable', 'ic_launcher_foreground.png'))
print("Generated drawable/ic_launcher_foreground.png")

print("All App Icons & Helmet Logos generated successfully!")
