import os
from PIL import Image, ImageDraw

res_dir = r'app/src/main/res'

# Remove duplicate png files in mipmaps
densities = ['mipmap-mdpi', 'mipmap-hdpi', 'mipmap-xhdpi', 'mipmap-xxhdpi', 'mipmap-xxxhdpi']
for folder in densities:
    folder_path = os.path.join(res_dir, folder)
    for name in ['ic_launcher.png', 'ic_launcher_round.png']:
        p = os.path.join(folder_path, name)
        if os.path.exists(p):
            os.remove(p)
            print("Removed", p)

# Remove duplicate ic_launcher_foreground.png
p_fg = os.path.join(res_dir, 'drawable', 'ic_launcher_foreground.png')
if os.path.exists(p_fg):
    os.remove(p_fg)
    print("Removed", p_fg)

print("Duplicates cleaned up!")
