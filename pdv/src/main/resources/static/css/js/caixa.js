// Função para simular a finalização de uma venda
function finalizarVenda(dadosCarrinho) {
    const venda = {
        itens: dadosCarrinho,
        total: dadosCarrinho.reduce((acc, item) => acc + item.preco, 0),
        data: new Date().toISOString(),
        status: 'pendente'
    };

    // Verifica se há internet no Electron
    if (navigator.onLine) {
        enviarParaServidor(venda);
    } else {
        salvarLocalmente(venda);
    }
}

// Envia para o seu backend Java/Spring Boot nonp Render
function enviarParaServidor(venda) {
    fetch('/api/vendas', {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify(venda)
    })
        .then(response => {
            if (response.ok) {
                alert("Venda enviada com sucesso ao servidor!");
            }
        })
        .catch(() => salvarLocalmente(venda));
}

// Salva no LocalStorage do computador (Modo Offline)
function salvarLocalmente(venda) {
    let vendasOffline = JSON.parse(localStorage.getItem('vendas_pdv') || "[]");
    vendasOffline.push(venda);
    localStorage.setItem('vendas_pdv', JSON.stringify(vendasOffline));

    alert("SEM INTERNET: Venda salva no computador. Ela será enviada assim que a conexão voltar.");
    console.log("Vendas aguardando sincronização: ", vendasOffline.length);
}

// Ouve quando a internet volta para sincronizar tudo automaticamente
window.addEventListener('online', () => {
    const pendentes = JSON.parse(localStorage.getItem('vendas_pdv') || "[]");
    if (pendentes.length > 0) {
        alert("Internet restabelecida! Sincronizando vendas pendentes...");
        // Aqui você chamaria uma função para enviar o lote de vendas ao Java
        // Após sucesso: localStorage.removeItem('vendas_pdv');
    }
});