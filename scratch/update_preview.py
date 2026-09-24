import re

with open('gen_preview.py', 'r', encoding='utf-8') as f:
    content = f.read()

profile_screen_html = """
    <!-- ============================================================== -->
    <!-- PANTALLA: MI PERFIL BIKER -->
    <!-- ============================================================== -->
    <div id="screenProfile" class="hidden flex flex-col h-full w-full relative bg-gray-50 overflow-hidden">
      <!-- Top Header Bar -->
      <div class="flex items-center justify-between px-5 pt-4 pb-2 z-10 bg-white/90 backdrop-blur-xs border-b border-gray-100 shrink-0">
        <div class="flex items-center space-x-2">
          <button onclick="goToHome()" class="text-gray-900 font-bold p-1 hover:opacity-70 active:scale-90 transition-all cursor-pointer">
            <svg class="w-5 h-5" fill="none" stroke="currentColor" stroke-width="2.5" viewBox="0 0 24 24"><path stroke-linecap="round" stroke-linejoin="round" d="M15 19l-7-7 7-7"/></svg>
          </button>
          <h2 class="vintage-shaded text-xl font-bold text-gray-900">Mi Perfil Biker</h2>
        </div>
        <div class="flex items-center space-x-1.5 opacity-60">
          <img src="{helmet_b64}" class="w-6 h-6 object-contain" alt="Logo" />
        </div>
      </div>

      <!-- Scrollable Form Body -->
      <div class="flex-1 overflow-y-auto p-4 space-y-4 z-10 pb-20">
        <!-- Avatar / User Header Card -->
        <div class="bg-gray-900 rounded-2xl p-5 text-white shadow-lg flex items-center space-x-4">
          <div class="w-16 h-16 rounded-full bg-amber-500 text-gray-900 font-black text-2xl flex items-center justify-center shadow-md shrink-0 border-2 border-white">
            <span id="profileAvatarInitials">MB</span>
          </div>
          <div class="flex-1 min-w-0">
            <h3 id="profileHeaderName" class="text-base font-black truncate">Marcelo Biker</h3>
            <p id="profileHeaderEmail" class="text-xs text-gray-300 truncate">marcelo@chelobiker.com</p>
            <span class="inline-block mt-1 px-2.5 py-0.5 rounded-full bg-amber-500/20 text-amber-400 text-[10px] font-bold tracking-wider">PILOTO ACTIVO 🏁</span>
          </div>
        </div>

        <!-- Card 1: Información Personal -->
        <div class="bg-white rounded-2xl p-4 shadow-sm border border-gray-100 space-y-3">
          <div class="flex items-center space-x-2 pb-1 border-b border-gray-100">
            <span class="text-base">👤</span>
            <h4 class="text-xs font-bold text-gray-800 uppercase tracking-wider">Información Personal</h4>
          </div>

          <div>
            <label class="block text-[11px] font-bold text-gray-600 mb-1">Nombre Completo</label>
            <input type="text" id="profName" value="Marcelo Biker" class="w-full bg-gray-50 border border-gray-200 rounded-xl px-3 py-2 text-xs font-semibold text-gray-800 focus:outline-none focus:border-black" />
          </div>

          <div>
            <label class="block text-[11px] font-bold text-gray-600 mb-1">Correo Electrónico</label>
            <input type="email" id="profEmail" value="marcelo@chelobiker.com" class="w-full bg-gray-50 border border-gray-200 rounded-xl px-3 py-2 text-xs font-semibold text-gray-800 focus:outline-none focus:border-black" />
          </div>

          <div class="grid grid-cols-2 gap-2">
            <div>
              <label class="block text-[11px] font-bold text-gray-600 mb-1">Celular / WhatsApp</label>
              <input type="text" id="profPhone" value="+591 76543210" class="w-full bg-gray-50 border border-gray-200 rounded-xl px-3 py-2 text-xs font-semibold text-gray-800 focus:outline-none focus:border-black" />
            </div>
            <div>
              <label class="block text-[11px] font-bold text-gray-600 mb-1">C.I. / NIT</label>
              <input type="text" id="profNit" value="8492019 LP" class="w-full bg-gray-50 border border-gray-200 rounded-xl px-3 py-2 text-xs font-semibold text-gray-800 focus:outline-none focus:border-black" />
            </div>
          </div>

          <div>
            <label class="block text-[11px] font-bold text-gray-600 mb-1">Ciudad Principal</label>
            <select id="profCity" class="w-full bg-gray-50 border border-gray-200 rounded-xl px-3 py-2 text-xs font-semibold text-gray-800 focus:outline-none focus:border-black">
              <option value="La Paz (MegaCenter Irpavi)">La Paz (MegaCenter Irpavi)</option>
              <option value="El Alto (Cruce Viacha)">El Alto (Cruce Viacha)</option>
              <option value="Cochabamba (Plaza Principal)">Cochabamba (Plaza Principal)</option>
              <option value="Santa Cruz">Santa Cruz</option>
            </select>
          </div>
        </div>

        <!-- Card 2: Garaje Biker -->
        <div class="bg-white rounded-2xl p-4 shadow-sm border border-gray-100 space-y-3">
          <div class="flex items-center space-x-2 pb-1 border-b border-gray-100">
            <span class="text-base">🏍️</span>
            <h4 class="text-xs font-bold text-gray-800 uppercase tracking-wider">Mi Garaje Biker</h4>
          </div>

          <div>
            <label class="block text-[11px] font-bold text-gray-600 mb-1">Moto Actual o Favorita</label>
            <input type="text" id="profMoto" value="KTM POWER 250CC" placeholder="Ej: BMW S1000RR / KTM 250" class="w-full bg-gray-50 border border-gray-200 rounded-xl px-3 py-2 text-xs font-semibold text-gray-800 focus:outline-none focus:border-black" />
          </div>

          <div>
            <label class="block text-[11px] font-bold text-gray-600 mb-1">Licencia de Conducir</label>
            <input type="text" id="profLicense" value="Categoría M (Motociclista)" placeholder="Ej: Cat. M / Profesional" class="w-full bg-gray-50 border border-gray-200 rounded-xl px-3 py-2 text-xs font-semibold text-gray-800 focus:outline-none focus:border-black" />
          </div>
        </div>

        <!-- Card 3: Emergencia y Seguridad -->
        <div class="bg-white rounded-2xl p-4 shadow-sm border border-gray-100 space-y-3">
          <div class="flex items-center space-x-2 pb-1 border-b border-gray-100">
            <span class="text-base">🚨</span>
            <h4 class="text-xs font-bold text-gray-800 uppercase tracking-wider">Seguridad Biker</h4>
          </div>

          <div class="grid grid-cols-2 gap-2">
            <div>
              <label class="block text-[11px] font-bold text-gray-600 mb-1">Grupo Sanguíneo</label>
              <input type="text" id="profBlood" value="ORH+" placeholder="Ej: O+, A+, B+" class="w-full bg-gray-50 border border-gray-200 rounded-xl px-3 py-2 text-xs font-semibold text-gray-800 focus:outline-none focus:border-black" />
            </div>
            <div>
              <label class="block text-[11px] font-bold text-gray-600 mb-1">Contacto Emergencia</label>
              <input type="text" id="profEmergency" value="Esposa / 71234567" placeholder="Nombre y Teléfono" class="w-full bg-gray-50 border border-gray-200 rounded-xl px-3 py-2 text-xs font-semibold text-gray-800 focus:outline-none focus:border-black" />
            </div>
          </div>
        </div>

        <!-- Actions -->
        <div class="pt-2 space-y-2">
          <button onclick="saveUserProfile()" class="w-full py-3.5 bg-gray-900 hover:bg-black active:scale-95 text-white font-serif font-black text-xs rounded-2xl tracking-wider shadow-lg flex items-center justify-center space-x-2 transition-all cursor-pointer">
            <span>💾</span>
            <span>GUARDAR INFORMACIÓN</span>
          </button>
          <button onclick="handleLogout()" class="w-full py-3 bg-red-50 hover:bg-red-100 active:scale-95 text-red-600 font-bold text-xs rounded-2xl border border-red-200 transition-all cursor-pointer flex items-center justify-center space-x-1.5">
            <span>🚪</span>
            <span>CERRAR SESIÓN</span>
          </button>
        </div>
      </div>

      <!-- Bottom Navigation Bar (Profile Active) -->
      <div class="h-16 bg-white border-t border-gray-100 flex items-center justify-around px-8 z-30 shadow-2xl shrink-0">
        <button onclick="goToProfile()" class="flex flex-col items-center justify-center p-2 text-black transition-all scale-110 cursor-pointer">
          <svg class="w-7 h-7" fill="currentColor" viewBox="0 0 24 24"><path d="M12 12c2.21 0 4-1.79 4-4s-1.79-4-4-4-4 1.79-4 4 1.79 4 4 4zm0 2c-2.67 0-8 1.34-8 4v2h16v-2c0-2.66-5.33-4-8-4z"/></svg>
        </button>
        <button onclick="goToHome()" class="flex flex-col items-center justify-center p-2 text-gray-400 hover:text-black transition-all cursor-pointer">
          <svg class="w-7 h-7" fill="currentColor" viewBox="0 0 24 24"><path d="M10 20v-6h4v6h5v-8h3L12 3 2 12h3v8z"/></svg>
        </button>
        <button onclick="goToBranchesSelection()" class="flex flex-col items-center justify-center p-2 text-gray-400 hover:text-black transition-all cursor-pointer">
          <svg class="w-7 h-7" fill="currentColor" viewBox="0 0 24 24"><path d="M12 2C8.13 2 5 5.13 5 9c0 5.25 7 13 7 13s7-7.75 7-13c0-3.87-3.13-7-7-7zm0 9.5c-1.38 0-2.5-1.12-2.5-2.5s1.12-2.5 2.5-2.5 2.5 1.12 2.5 2.5-1.12 2.5-2.5 2.5z"/></svg>
        </button>
      </div>
    </div>
"""

# Replace showToast('Ruta: Perfil 👤') with goToProfile()
content = content.replace("showToast('Ruta: Perfil 👤')", "goToProfile()")

# Add goToProfile() and profile JS logic
profile_js = """
    function goToProfile() {{
      hideAllScreens();
      document.getElementById('screenProfile').classList.remove('hidden');
      showFloatingCart(false);
      showToast('Mi Perfil Biker 👤');
    }}

    function saveUserProfile() {{
      const name = document.getElementById('profName').value.trim() || 'Marcelo Biker';
      const email = document.getElementById('profEmail').value.trim() || 'marcelo@chelobiker.com';
      
      document.getElementById('profileHeaderName').innerText = name;
      document.getElementById('profileHeaderEmail').innerText = email;
      
      const initials = name.split(' ').map(n => n[0]).join('').substring(0, 2).toUpperCase() || 'MB';
      document.getElementById('profileAvatarInitials').innerText = initials;
      
      showToast('¡Información guardada con éxito! ✅');
    }}

    function handleLogout() {{
      if (confirm('¿Deseas cerrar tu sesión actual de Chelo Biker?')) {{
        goToLogin();
        showToast('Sesión cerrada');
      }}
    }}
"""

# Add screenProfile to hideAllScreens()
content = content.replace("document.getElementById('screenBranchMap').classList.add('hidden');", "document.getElementById('screenBranchMap').classList.add('hidden');\\n      document.getElementById('screenProfile').classList.add('hidden');")

# Inject screenProfile before screenMotosCatalog
content = content.replace('<!-- PANTALLA 4: CATÁLOGO DE MOTOS (DISPONIBLES) -->', profile_screen_html + '\\n    <!-- PANTALLA 4: CATÁLOGO DE MOTOS (DISPONIBLES) -->')

# Inject profile JS before hideAllScreens
content = content.replace('function hideAllScreens()', profile_js + '\\n    function hideAllScreens()')

# Write back to gen_preview.py
with open('gen_preview.py', 'w', encoding='utf-8') as f:
    f.write(content)

print("Updated gen_preview.py successfully!")
