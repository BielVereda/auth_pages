document.addEventListener("DOMContentLoaded", () => {
  const form = document.getElementById("loginForm");
  if (!form) return;

  form.addEventListener("submit", async (event) => {
    event.preventDefault();

    const pageTitle = document.title.toLowerCase();

    try {
      if (pageTitle.includes("signin")) {
        // ===== LOGIN =====
        const email = document.getElementById("email").value.trim();
        const password = document.getElementById("password").value.trim();

        if (!email || !password) {
          alert("⚠️ Por favor, preencha todos os campos antes de entrar.");
          return;
        }

        const response = await fetch("http://localhost:8080/login", {
          method: "POST",
          headers: { "Content-Type": "application/json" },
          body: JSON.stringify({ email, password }),
        });

        const data = await response.text();
        alert(data);

        // ✅ Limpa os campos depois que o alerta fecha
        form.reset();

      } else if (pageTitle.includes("signup")) {
        // ===== CADASTRO =====
        const name = document.getElementById("name").value.trim();
        const email = document.getElementById("email").value.trim();
        const password = document.getElementById("password").value.trim();

        if (!name || !email || !password) {
          alert("⚠️ Preencha todos os campos para se cadastrar.");
          return;
        }

        const response = await fetch("http://localhost:8080/cadastro", {
          method: "POST",
          headers: { "Content-Type": "application/json" },
          body: JSON.stringify({ name, email, password }),
        });

        const data = await response.text();
        alert(data);

        // ✅ Limpa os campos depois que o alerta fecha
        form.reset();
      }
    } catch (error) {
      console.error("Erro:", error);
      alert("⚠️ Erro ao conectar ao servidor.");
    }
  });
});