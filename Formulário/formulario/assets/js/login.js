const form = document.getElementById("loginForm");

    form.addEventListener("submit", async (event) => {
      event.preventDefault(); // impede o reload da página

      const email = document.getElementById("email").value;
      const password = document.getElementById("password").value;

      // Corpo da requisição em JSON
      const user = { email, password };

      try {
        const response = await fetch("http://localhost:8080/login", {
          method: "POST",
          headers: {
            "Content-Type": "application/json"
          },
          body: JSON.stringify(user)
        });

        const data = await response.text(); // sua API retorna texto simples
        alert(data); // exibe a resposta ("Usuário logado", "Senha incorreta", etc.)

      } catch (error) {
        console.error("Erro:", error);
        alert("Erro ao conectar ao servidor.");
      }
    });