const { app, BrowserWindow, Menu } = require('electron');
const path = require('path');

function createWindow() {
    // 1. Configuração da Janela
    const win = new BrowserWindow({
        width: 1280,
        height: 800,
        title: "PdvWeb - Sistema de Gestão", // Nome na barra de título
        icon: path.join(__dirname, 'src/main/resources/static/favicon.ico'), // Opcional: seu ícone
        webPreferences: {
            nodeIntegration: false,
            contextIsolation: true
        }
    });

    // 2. Remove a barra de menus (File, Edit, View, etc.)
    Menu.setApplicationMenu(null);
    win.setMenu(null);

    // 3. Definição das URLs
    const urlOnline = 'https://pdvweb.onrender.com';
    const localFile = path.join(__dirname, 'src/main/resources/static/index.html');

    // 4. Lógica de Carregamento (Online com Fallback para Offline)
    win.loadURL(urlOnline).catch((err) => {
        console.log("Falha ao conectar com o Render. Carregando modo offline...");

        win.loadFile(localFile).then(() => {
            // Altera o título para avisar o usuário que está offline
            win.setTitle("PdvWeb - Modo Offline (Sem Internet)");
        }).catch((fileErr) => {
            console.error("Erro crítico: Arquivo local não encontrado", fileErr);
        });
    });
}

// Inicialização do App
app.whenReady().then(() => {
    createWindow();

    app.on('activate', () => {
        if (BrowserWindow.getAllWindows().length === 0) createWindow();
    });
});

// Fecha o app quando todas as janelas forem fechadas (exceto no Mac)
app.on('window-all-closed', () => {
    if (process.platform !== 'darwin') app.quit();
});