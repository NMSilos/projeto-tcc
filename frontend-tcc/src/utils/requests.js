export const baseUrl = "http://localhost:8080/";

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
  if (method !== "GET") {
    config.body = JSON.stringify(dados);
  }

  const response = await fetch(finalUrl, config);

  if (!response.ok) {
    const dados = await response.json();
    throw new Error(dados.mensagem);
  }

  const contentType = response.headers.get("content-type");
  if (contentType && contentType.includes("application/json")) {
    const dados = await response.json();
    return dados;
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

export async function requestFormData(url, dados, method) {
  const finalUrl = baseUrl + url;
  const token = localStorage.getItem("token");
  if (!dados.imagem) {
    dados.imagem = null;
  }

  const formData = new FormData();
  for (const index in dados) {
    formData.append(index, dados[index]);
  }

  const response = "";

  if (token != null) {
    response = await fetch(finalUrl, {
      method: method,
      body: formData,
      headers: {
        Authorization: `Bearer ${token}`
      },
    });
  } else {
    response = await fetch(finalUrl, {
      method: method,
      body: formData,
    });
  }

  if (response.ok) {
    const dados = await response.json();
    return dados;
  } else {
    const dados = await response.json();
    throw new Error(dados.mensage);
  }
}

export async function buscaLivros(url, method) {
  const finalUrl = baseUrl + url;

  const response = await fetch(finalUrl, {
    method: method,
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

export async function buscaSugestoes(url, method) {
  const finalUrl = baseUrl + url;

  const response = await fetch(finalUrl, {
    method: method,
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