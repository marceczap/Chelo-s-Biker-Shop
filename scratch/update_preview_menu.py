import re

with open('gen_preview.py', 'r', encoding='utf-8') as f:
    content = f.read()

# 1. Remove Cerrar Sesion button from screenProfile in gen_preview.py
old_profile_actions = """        <!-- Actions -->
        <div class="pt-2 space-y-2">
          <button onclick="saveUserProfile()" class="w-full py-3.5 bg-gray-900 hover:bg-black active:scale-95 text-white font-serif font-black text-xs rounded-2xl tracking-wider shadow-lg flex items-center justify-center space-x-2 transition-all cursor-pointer">
            <span>💾</span>
            <span>GUARDAR INFORMACIÓN</span>
          </button>
          <button onclick="handleLogout()" class="w-full py-3 bg-red-50 hover:bg-red-100 active:scale-95 text-red-600 font-bold text-xs rounded-2xl border border-red-200 transition-all cursor-pointer flex items-center justify-center space-x-1.5">
            <span>🚪</span>
            <span>CERRAR SESIÓN</span>
          </button>
        </div>"""

new_profile_actions = """        <!-- Actions -->
        <div class="pt-2">
          <button onclick="saveUserProfile()" id="btnSaveProfile" class="w-full py-3.5 bg-gray-900 hover:bg-black active:scale-95 text-white font-serif font-black text-xs rounded-2xl tracking-wider shadow-lg flex items-center justify-center space-x-2 transition-all cursor-pointer">
            <span>💾</span>
            <span id="txtSaveProfileBtn">GUARDAR INFORMACIÓN</span>
          </button>
        </div>"""

if old_profile_actions in content:
    content = content.replace(old_profile_actions, new_profile_actions)

# 2. Update Home Top Bar in gen_preview.py to have Profile on top left and Hamburger Menu on top right
old_home_header = """        <!-- Top Header Navigation with HOME Centered -->
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
        </div>"""

new_home_header = """        <!-- Top Header Navigation with HOME Centered, Profile and Hamburger Menu -->
        <div class="relative z-10 flex items-center justify-between w-full">
          <button onclick="goToProfile()" class="w-9 h-9 bg-black/40 hover:bg-black/60 rounded-full text-white flex items-center justify-center backdrop-blur-xs cursor-pointer transition-all active:scale-90" title="Mi Perfil">
            <svg class="w-5 h-5 fill-current" viewBox="0 0 24 24"><path d="M12 12c2.21 0 4-1.79 4-4s-1.79-4-4-4-4 1.79-4 4 1.79 4 4 4zm0 2c-2.67 0-8 1.34-8 4v2h16v-2c0-2.66-5.33-4-8-4z"/></svg>
          </button>
          
          <!-- Perfectly Centered HOME & Logo -->
          <div class="absolute inset-x-0 flex items-center justify-center space-x-2 pointer-events-none">
            <span class="font-bold text-white text-base tracking-widest drop-shadow font-serif">HOME</span>
            <img src="{helmet_b64}" class="w-8 h-8 object-contain filter invert drop-shadow" alt="Logo" />
          </div>

          <button onclick="openHamburgerMenu()" class="w-9 h-9 bg-black/40 hover:bg-black/60 rounded-full text-white flex items-center justify-center backdrop-blur-xs cursor-pointer transition-all active:scale-90" title="Menú Hamburguesa">
            <svg class="w-5 h-5" fill="none" stroke="currentColor" stroke-width="2.5" viewBox="0 0 24 24"><path stroke-linecap="round" stroke-linejoin="round" d="M4 6h16M4 12h16M4 18h16"/></svg>
          </button>
        </div>"""

if old_home_header in content:
    content = content.replace(old_home_header, new_home_header)

# Replace all third bottom nav buttons to call openHamburgerMenu()
content = re.sub(r'onclick="goToBranchesSelection\(\)"(\s+class="[^"]*cursor-pointer")', r'onclick="openHamburgerMenu()"\1', content)

# 3. Add Hamburger Menu Modal + Contact Modal + Legal Modal HTML before </body>
modals_html = """
    <!-- ============================================== -->
    <!-- MODAL 1: MENÚ HAMBURGUESA BOTTOM SHEET -->
    <!-- ============================================== -->
    <div id="modalHamburger" class="fixed inset-0 bg-black/60 backdrop-blur-xs z-50 flex flex-col justify-end hidden opacity-0 transition-opacity duration-300">
      <div class="bg-white w-full rounded-t-[32px] p-6 shadow-2xl flex flex-col space-y-4 max-h-[85vh] overflow-y-auto transform translate-y-full transition-transform duration-300" id="hamburgerPanel">
        <!-- Drag Handle -->
        <div class="w-12 h-1 bg-gray-300 rounded-full mx-auto -mt-2 mb-1"></div>

        <!-- Header -->
        <div class="flex items-center justify-between pb-2 border-b border-gray-100">
          <div class="flex items-center space-x-3">
            <img src="{helmet_b64}" class="w-9 h-9 object-contain" alt="Logo" />
            <div>
              <h3 id="txtMenuTitle" class="font-serif font-bold text-gray-900 text-sm tracking-wider">CHELO BIKER SHOP</h3>
              <p class="text-[11px] text-gray-500 font-medium">Marcelo Biker • marcelo@chelobiker.com</p>
            </div>
          </div>
          <button onclick="closeHamburgerMenu()" class="w-8 h-8 rounded-full bg-gray-100 hover:bg-gray-200 flex items-center justify-center text-gray-700 cursor-pointer">✕</button>
        </div>

        <!-- 1. CONTÁCTANOS -->
        <button onclick="openContactModal()" class="w-full flex items-center justify-between p-3.5 bg-gray-50 hover:bg-gray-100 rounded-2xl border border-gray-100 transition-all cursor-pointer text-left active:scale-[0.98]">
          <div class="flex items-center space-x-3.5">
            <div class="w-10 h-10 rounded-full bg-blue-50 text-blue-600 flex items-center justify-center text-lg">📞</div>
            <div>
              <div id="txtMenuContact" class="font-serif font-bold text-xs text-gray-900">CONTÁCTANOS</div>
              <div id="txtMenuContactSub" class="text-[10.5px] text-gray-500">WhatsApp, llamadas, correo y sucursales</div>
            </div>
          </div>
          <span class="text-gray-400 font-bold text-sm">→</span>
        </button>

        <!-- 2. INFORMACIÓN LEGAL -->
        <button onclick="openLegalModal()" class="w-full flex items-center justify-between p-3.5 bg-gray-50 hover:bg-gray-100 rounded-2xl border border-gray-100 transition-all cursor-pointer text-left active:scale-[0.98]">
          <div class="flex items-center space-x-3.5">
            <div class="w-10 h-10 rounded-full bg-purple-50 text-purple-600 flex items-center justify-center text-lg">📄</div>
            <div>
              <div id="txtMenuLegal" class="font-serif font-bold text-xs text-gray-900">INFORMACIÓN LEGAL</div>
              <div id="txtMenuLegalSub" class="text-[10.5px] text-gray-500">Términos de servicio, garantías y privacidad</div>
            </div>
          </div>
          <span class="text-gray-400 font-bold text-sm">→</span>
        </button>

        <!-- 3. IDIOMA / LANGUAGE -->
        <div class="w-full p-4 bg-gray-50 rounded-2xl border border-gray-100 space-y-3">
          <div class="flex items-center space-x-3">
            <div class="w-10 h-10 rounded-full bg-emerald-50 text-emerald-600 flex items-center justify-center text-lg">🌐</div>
            <div>
              <div id="txtMenuLang" class="font-serif font-bold text-xs text-gray-900">IDIOMA / LANGUAGE</div>
              <div id="txtMenuLangSub" class="text-[10.5px] text-gray-500">Selecciona el idioma del aplicativo</div>
            </div>
          </div>

          <!-- Language switch buttons -->
          <div class="grid grid-cols-2 gap-2.5 pt-1">
            <button id="btnLangEs" onclick="setAppLanguage('es')" class="py-2.5 px-3 rounded-xl font-bold text-xs flex items-center justify-center space-x-1.5 transition-all bg-gray-900 text-white shadow-sm cursor-pointer">
              <span>🇪🇸</span>
              <span>Español</span>
            </button>
            <button id="btnLangEn" onclick="setAppLanguage('en')" class="py-2.5 px-3 rounded-xl font-bold text-xs flex items-center justify-center space-x-1.5 transition-all bg-white text-gray-700 border border-gray-200 hover:bg-gray-100 cursor-pointer">
              <span>🇺🇸</span>
              <span>English</span>
            </button>
          </div>
        </div>

        <!-- 4. CERRAR SESIÓN -->
        <button onclick="handleLogoutFromMenu()" class="w-full flex items-center space-x-3.5 p-3.5 bg-red-50 hover:bg-red-100 rounded-2xl border border-red-100 text-red-600 transition-all cursor-pointer text-left active:scale-[0.98]">
          <div class="w-10 h-10 rounded-full bg-red-100 text-red-600 flex items-center justify-center text-lg">🚪</div>
          <div>
            <div id="txtMenuLogout" class="font-serif font-bold text-xs text-red-700">CERRAR SESIÓN</div>
            <div id="txtMenuLogoutSub" class="text-[10.5px] text-red-500">Cerrar la cuenta en este dispositivo</div>
          </div>
        </button>
      </div>
    </div>

    <!-- ============================================== -->
    <!-- MODAL 2: CONTÁCTANOS (CONTACT US) -->
    <!-- ============================================== -->
    <div id="modalContact" class="fixed inset-0 bg-black/60 backdrop-blur-xs z-50 flex items-center justify-center p-4 hidden opacity-0 transition-opacity duration-300">
      <div class="bg-white w-full max-w-sm rounded-[28px] p-5 shadow-2xl space-y-4">
        <div class="flex items-center justify-between pb-2 border-b border-gray-100">
          <div class="flex items-center space-x-2">
            <span class="text-xl">📞</span>
            <h3 id="txtContactModalTitle" class="font-serif font-bold text-base text-gray-900">Contáctanos</h3>
          </div>
          <button onclick="closeContactModal()" class="w-7 h-7 rounded-full bg-gray-100 hover:bg-gray-200 flex items-center justify-center text-gray-700 cursor-pointer">✕</button>
        </div>

        <div class="space-y-2.5 text-xs">
          <!-- WhatsApp -->
          <a href="https://api.whatsapp.com/send?phone=+59176543210&text=Hola%20Chelo%20Biker%20Shop" target="_blank" class="flex items-center justify-between p-3 bg-emerald-50 rounded-2xl border border-emerald-100 hover:bg-emerald-100 transition-all text-emerald-900 font-medium">
            <div class="flex items-center space-x-2.5">
              <span class="text-lg">💬</span>
              <div>
                <div class="font-bold text-emerald-800">WhatsApp Oficial</div>
                <div class="text-[11px] text-emerald-600">+591 76543210 (24/7)</div>
              </div>
            </div>
            <span class="px-2.5 py-1 bg-emerald-600 text-white rounded-full text-[10px] font-bold">CHATEAR</span>
          </a>

          <!-- Teléfono -->
          <a href="tel:+59176543210" class="flex items-center justify-between p-3 bg-sky-50 rounded-2xl border border-sky-100 hover:bg-sky-100 transition-all text-sky-900 font-medium">
            <div class="flex items-center space-x-2.5">
              <span class="text-lg">📞</span>
              <div>
                <div class="font-bold text-sky-800">Central Telefónica</div>
                <div class="text-[11px] text-sky-600">+591 2 2789000</div>
              </div>
            </div>
            <span class="px-2.5 py-1 bg-sky-600 text-white rounded-full text-[10px] font-bold">LLAMAR</span>
          </a>

          <!-- Correo -->
          <a href="mailto:contacto@chelobiker.com" class="flex items-center justify-between p-3 bg-gray-50 rounded-2xl border border-gray-200 hover:bg-gray-100 transition-all text-gray-900 font-medium">
            <div class="flex items-center space-x-2.5">
              <span class="text-lg">✉️</span>
              <div>
                <div class="font-bold text-gray-800">Correo Electrónico</div>
                <div class="text-[11px] text-gray-500">contacto@chelobiker.com</div>
              </div>
            </div>
            <span class="px-2.5 py-1 bg-gray-800 text-white rounded-full text-[10px] font-bold">ENVIAR</span>
          </a>

          <!-- Horario y Dirección -->
          <div class="p-3 bg-amber-50/60 rounded-2xl border border-amber-100 text-[11px] text-amber-900">
            <div class="font-bold">📍 Casa Matriz & Sucursales:</div>
            <div>Av. Rafael Pabón, MegaCenter Irpavi • La Paz, Bolivia</div>
            <div class="mt-1 font-semibold text-amber-800">⏰ Horario: Lun a Sáb de 09:00 a 19:30</div>
          </div>
        </div>

        <button onclick="closeContactModal()" class="w-full py-2.5 bg-gray-900 hover:bg-black text-white rounded-xl text-xs font-bold transition-all cursor-pointer">CERRAR</button>
      </div>
    </div>

    <!-- ============================================== -->
    <!-- MODAL 3: INFORMACIÓN LEGAL (LEGAL INFORMATION) -->
    <!-- ============================================== -->
    <div id="modalLegal" class="fixed inset-0 bg-black/60 backdrop-blur-xs z-50 flex items-center justify-center p-4 hidden opacity-0 transition-opacity duration-300">
      <div class="bg-white w-full max-w-sm rounded-[28px] p-5 shadow-2xl space-y-4">
        <div class="flex items-center justify-between pb-2 border-b border-gray-100">
          <div class="flex items-center space-x-2">
            <span class="text-xl">📄</span>
            <h3 id="txtLegalModalTitle" class="font-serif font-bold text-base text-gray-900">Información Legal</h3>
          </div>
          <button onclick="closeLegalModal()" class="w-7 h-7 rounded-full bg-gray-100 hover:bg-gray-200 flex items-center justify-center text-gray-700 cursor-pointer">✕</button>
        </div>

        <div class="max-h-60 overflow-y-auto space-y-3 text-[11px] text-gray-600 leading-relaxed pr-1">
          <div>
            <span class="font-bold text-gray-900 block text-xs">1. TÉRMINOS Y CONDICIONES DE USO</span>
            Chelo Biker Shop S.R.L. ofrece comercialización oficial de motocicletas de alta cilindrada, cascos homologados ECE 22.06 / DOT e indumentaria con protecciones de impacto nivel CE 2 en Bolivia.
          </div>
          <div>
            <span class="font-bold text-gray-900 block text-xs">2. POLÍTICA DE PRIVACIDAD</span>
            Todos los datos recopilados (Nombre, NIT/CI, teléfono y garaje) son tratados de forma confidencial para gestión de pedidos y entrega en sucursal.
          </div>
          <div>
            <span class="font-bold text-gray-900 block text-xs">3. GARANTÍA OFICIAL BIKER</span>
            1 año o 10,000 km de garantía de fábrica en motocicletas. Registro oficial ante el Servicio de Impuestos Nacionales (SIN).
          </div>
        </div>

        <button onclick="closeLegalModal()" class="w-full py-2.5 bg-gray-900 hover:bg-black text-white rounded-xl text-xs font-bold transition-all cursor-pointer">ENTENDIDO</button>
      </div>
    </div>
"""

# Inject modals before </body>
content = content.replace('</body>', modals_html + '\n</body>')

# Add JS functions for Hamburger Menu, Language switcher, Contact & Legal modals with double curly braces for f-strings
js_hamburger = """
    // ==========================================
    // HAMBURGER MENU & LOCALIZATION (ES / EN)
    // ==========================================
    let currentAppLanguage = 'es';

    const translations = {{
      es: {{
        menuTitle: 'CHELO BIKER SHOP',
        menuContact: 'CONTÁCTANOS',
        menuContactSub: 'WhatsApp, llamadas, correo y sucursales',
        menuLegal: 'INFORMACIÓN LEGAL',
        menuLegalSub: 'Términos de servicio, garantías y privacidad',
        menuLang: 'IDIOMA / LANGUAGE',
        menuLangSub: 'Selecciona el idioma del aplicativo',
        menuLogout: 'CERRAR SESIÓN',
        menuLogoutSub: 'Cerrar la cuenta en este dispositivo',
        contactTitle: 'Contáctanos',
        legalTitle: 'Información Legal',
        saveProfileBtn: 'GUARDAR INFORMACIÓN',
        searchPlaceholder: 'Buscar motos, cascos, repuestos...'
      }},
      en: {{
        menuTitle: 'CHELO BIKER SHOP',
        menuContact: 'CONTACT US',
        menuContactSub: 'WhatsApp, phone calls, email & branches',
        menuLegal: 'LEGAL INFORMATION',
        menuLegalSub: 'Terms of service, privacy & warranties',
        menuLang: 'LANGUAGE / IDIOMA',
        menuLangSub: 'Choose your preferred application language',
        menuLogout: 'LOG OUT',
        menuLogoutSub: 'Sign out from your account on this device',
        contactTitle: 'Contact Us',
        legalTitle: 'Legal Information',
        saveProfileBtn: 'SAVE INFORMATION',
        searchPlaceholder: 'Search bikes, helmets, parts...'
      }}
    }};

    function openHamburgerMenu() {{
      const modal = document.getElementById('modalHamburger');
      const panel = document.getElementById('hamburgerPanel');
      modal.classList.remove('hidden');
      setTimeout(() => {{
        modal.classList.remove('opacity-0');
        panel.classList.remove('translate-y-full');
      }}, 10);
    }}

    function closeHamburgerMenu() {{
      const modal = document.getElementById('modalHamburger');
      const panel = document.getElementById('hamburgerPanel');
      modal.classList.add('opacity-0');
      panel.classList.add('translate-y-full');
      setTimeout(() => {{
        modal.classList.add('hidden');
      }}, 300);
    }}

    function openContactModal() {{
      closeHamburgerMenu();
      const modal = document.getElementById('modalContact');
      modal.classList.remove('hidden');
      setTimeout(() => modal.classList.remove('opacity-0'), 10);
    }}

    function closeContactModal() {{
      const modal = document.getElementById('modalContact');
      modal.classList.add('opacity-0');
      setTimeout(() => modal.classList.add('hidden'), 300);
    }}

    function openLegalModal() {{
      closeHamburgerMenu();
      const modal = document.getElementById('modalLegal');
      modal.classList.remove('hidden');
      setTimeout(() => modal.classList.remove('opacity-0'), 10);
    }}

    function closeLegalModal() {{
      const modal = document.getElementById('modalLegal');
      modal.classList.add('opacity-0');
      setTimeout(() => modal.classList.add('hidden'), 300);
    }}

    function setAppLanguage(lang) {{
      currentAppLanguage = lang;
      const t = translations[lang];

      // Update Menu UI
      document.getElementById('txtMenuTitle').innerText = t.menuTitle;
      document.getElementById('txtMenuContact').innerText = t.menuContact;
      document.getElementById('txtMenuContactSub').innerText = t.menuContactSub;
      document.getElementById('txtMenuLegal').innerText = t.menuLegal;
      document.getElementById('txtMenuLegalSub').innerText = t.menuLegalSub;
      document.getElementById('txtMenuLang').innerText = t.menuLang;
      document.getElementById('txtMenuLangSub').innerText = t.menuLangSub;
      document.getElementById('txtMenuLogout').innerText = t.menuLogout;
      document.getElementById('txtMenuLogoutSub').innerText = t.menuLogoutSub;
      document.getElementById('txtContactModalTitle').innerText = t.contactTitle;
      document.getElementById('txtLegalModalTitle').innerText = t.legalTitle;

      const saveBtn = document.getElementById('txtSaveProfileBtn');
      if (saveBtn) saveBtn.innerText = t.saveProfileBtn;

      const searchInput = document.getElementById('homeSearchInput');
      if (searchInput) searchInput.placeholder = t.searchPlaceholder;

      // Update button styles
      const btnEs = document.getElementById('btnLangEs');
      const btnEn = document.getElementById('btnLangEn');

      if (lang === 'en') {{
        btnEn.className = 'py-2.5 px-3 rounded-xl font-bold text-xs flex items-center justify-center space-x-1.5 transition-all bg-gray-900 text-white shadow-sm cursor-pointer';
        btnEs.className = 'py-2.5 px-3 rounded-xl font-bold text-xs flex items-center justify-center space-x-1.5 transition-all bg-white text-gray-700 border border-gray-200 hover:bg-gray-100 cursor-pointer';
        showToast('Language changed to English 🇺🇸');
      }} else {{
        btnEs.className = 'py-2.5 px-3 rounded-xl font-bold text-xs flex items-center justify-center space-x-1.5 transition-all bg-gray-900 text-white shadow-sm cursor-pointer';
        btnEn.className = 'py-2.5 px-3 rounded-xl font-bold text-xs flex items-center justify-center space-x-1.5 transition-all bg-white text-gray-700 border border-gray-200 hover:bg-gray-100 cursor-pointer';
        showToast('Idioma cambiado a Español 🇪🇸');
      }}
    }}

    function handleLogoutFromMenu() {{
      closeHamburgerMenu();
      if (confirm(currentAppLanguage === 'en' ? 'Are you sure you want to log out of Chelo Biker?' : '¿Deseas cerrar tu sesión actual de Chelo Biker?')) {{
        goToLogin();
        showToast(currentAppLanguage === 'en' ? 'Logged out successfully' : 'Sesión cerrada con éxito');
      }}
    }}
"""

content = content.replace('// Init', js_hamburger + '\n    // Init')

with open('gen_preview.py', 'w', encoding='utf-8') as f:
    f.write(content)

print("Updated gen_preview.py with Hamburger Menu & Localization!")
