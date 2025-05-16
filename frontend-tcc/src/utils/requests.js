const baseUrl = "http://localhost:8080/";

export async function requestLogado(url, dados, method) {
  const finalUrl = baseUrl + url;
  const token = localStorage.getItem("token");

  const config = {
    method: method,
    headers: {
      "Content-Type": "application/json",
      Authorization: `Bearer ${token}`,
    } 
  }
  if(method !== "GET") {
    config.body = JSON.stringify(dados);
  }

  const response = await fetch(finalUrl, config);
  if (response.ok) {
    const dados = await response.json();
    return dados;
  } else {
    if (response.status == 403) {
      //localStorage.removeItem("token");
      //window.location.href = "/";
    } else {
      const dados = await response.json();
      throw new Error(dados.mensagem);
    }
  }
}

export async function request(url, dados, method) {
  const finalUrl = baseUrl + url;

  const response = await fetch(finalUrl, {
    method: method,
    body: JSON.stringify(dados),
    headers: {
      "Content-Type": "application/json",
    },
  });
  if (response.ok) {
    const dados = await response.json();
    return dados;
  } else {
    const dados = await response.json();
    throw new Error(dados.mensage);
  }
}