# Compass.uol_Avaliacao_Sprint-5
Avaliação do Quinto Sprint do Programa de Bolsas Compass.UOL.

A avaliação consistia em criar uma REST API para cadastro de pedidos, simulando uma venda ou um carrinho de compras.

Segue abaixo a documentação da API e como consumi-la:

## Especificações
JDK 11.0.12

Spring Boot 2.7.1

server.port=8080

### Banco de Dados

Profile Padrão

    server.port=8080

    spring.profiles.active = dev

Profile de Testes

    application-test.properties

    jdbc:h2:mem:mercado

    url: /h2-console

    username: sa

    password:

Profile de Desenvolvimento

    jdbc:mysql://localhost:3306/mercado

    username: root

    password: root


## Endpoints
### /api/pedido
Endpoint principal da aplicação onde é possível realizar as principais requisições, são elas:
#### POST
Cadastra um novo Pedido na API. O id não precisa ser especificado, pois é gerado automaticamente pelo banco de dados, porém o CPF deve ser um CPF válido e não nulo, e o valor total também não deve ser nulo.

Caso seja cadastrado um Pedido válido, a API retornará um código 201 Created, e a mensagem: Pedido criado com sucesso!

Exemplo de entrada válida:

    {
        "cpf": "168.329.311-82",
        "itens": [
            {
                "nome": "Headset Gamer",
                "dataDeCriacao": "22/11/2030 07:06:45",
                "dataDeValidade": "16/07/2050 16:24:58",
                "valor": 249.90,
                "descricao": "Headset Gamer HyperX Cloud Stinger, Drivers 50mm, Múltiplas Plataformas, P2 e P3 - HX-HSCS-BK/NA",
                "ofertas": [
                    {
                    "nome": "Queima de Estoque",    
                    "dataDeCriacao": "30/12/2017 00:01:01",
                    "dataDeValidade": "01/12/2100 23:59:59",
                    "desconto": 10.00,
                    "descricao": "Incrível, todos nosso produtos com R$10,00 reais de desconto!!!"
                    },
                    {
                    "nome": "Promoção de Natal",    
                    "dataDeCriacao": "20/01/2010 00:01:01",
                    "dataDeValidade": "31/12/2022 23:59:59",
                    "desconto": 20.00,
                    "descricao": "Incrível, todos nosso produtos com R$20,00 reais de desconto!!!"
                    }
                ]
            },
            {
                "nome": "HD SSD",
                "dataDeCriacao": "13/01/2017 06:10:13",
                "dataDeValidade": "22/03/2022 16:24:58",
                "valor": 179.90,
                "descricao": "SSD Kingston A400, 240GB, SATA, Leitura 500MB/s, Gravação 350MB/s - SA400S37/240G"
            }
        ],
        "total": 2000
    }

#### GET

Devolve um array com todos os Pedidos cadastrados. E um status 200 ok.

    [
      {
          "id": 1,
          "cpf": "168.329.311-82",
          "itens": [
              {
                  "id": 1,
                  "nome": "Headset Gamer",
                  "dataDeCriacao": "22/11/2030 07:06:45",
                  "dataDeValidade": "22/11/2030 07:06:45",
                  "valor": 249.9,
                  "descricao": "Headset Gamer HyperX Cloud Stinger, Drivers 50mm, Múltiplas Plataformas, P2 e P3 - HX-HSCS-BK/NA",
                  "ofertas": [
                      {
                          "id": 1,
                          "nome": "Queima de Estoque",
                          "dataDeCriacao": "30/12/2017 00:01:01",
                          "dataDeValidade": "01/12/2100 23:59:59",
                          "desconto": 10.0,
                          "descricao": "Incrível, todos nosso produtos com R$10,00 reais de desconto!!!"
                      },
                      {
                          "id": 2,
                          "nome": "Promoção de Natal",
                          "dataDeCriacao": "20/01/2010 00:01:01",
                          "dataDeValidade": "31/12/2022 23:59:59",
                          "desconto": 20.0,
                          "descricao": "Incrível, todos nosso produtos com R$20,00 reais de desconto!!!"
                      }
                  ]
              },
              {
                  "id": 2,
                  "nome": "HD SSD",
                  "dataDeCriacao": "13/01/2017 06:10:13",
                  "dataDeValidade": "13/01/2017 06:10:13",
                  "valor": 179.9,
                  "descricao": "SSD Kingston A400, 240GB, SATA, Leitura 500MB/s, Gravação 350MB/s - SA400S37/240G",
                  "ofertas": []
              }
          ],
          "total": 2000.0
      }
    ]

Caso os Pedidos não existam a API simplesmente retorna um array vazio.

#### Parâmetros de Consulta

Este endpoint permite utilizar alguns Parâmetros de Consulta (Query Parameters) para filtrar e ordenar os resultados, são eles:

    sort = true e order=ASC ou DESC

Caso o sort seja igual a true o usuário deverá escolher o tipo de ordenação, Descendente(DESC) ou Ascendente(ASC). Essa ordenação é baseada no Valor Total do pedido.

Exemplo:

    /api/pedido?sort=true&order=ASC

##### filter (filtra pedidos por cpf)

Retorna os pedidos de um CPF específico.

Exemplo:

    api/pedido?filter=168.329.311-82

### /api/pedido/{id}

Este endpoint permite realizar requisições específicas de acordo com o id do Pedido cadastrado. Caso o id não esteja cadastrado a requisição retornará 404 Not Found.

#### GET

Recupera um Pedido pelo Id.

Exemplo:

    api/pedido/1

        {
        "id": 1,
        "cpf": "168.329.311-82",
        "itens": [
            {
                "id": 1,
                "nome": "Headset Gamer",
                "dataDeCriacao": "22/11/2030 07:06:45",
                "dataDeValidade": "22/11/2030 07:06:45",
                "valor": 249.9,
                "descricao": "Headset Gamer HyperX Cloud Stinger, Drivers 50mm, Múltiplas Plataformas, P2 e P3 - HX-HSCS-BK/NA",
                "ofertas": [
                    {
                        "id": 2,
                        "nome": "Promoção de Natal",
                        "dataDeCriacao": "20/01/2010 00:01:01",
                        "dataDeValidade": "31/12/2022 23:59:59",
                        "desconto": 20.0,
                        "descricao": "Incrível, todos nosso produtos com R$20,00 reais de desconto!!!"
                    },
                    {
                        "id": 1,
                        "nome": "Queima de Estoque",
                        "dataDeCriacao": "30/12/2017 00:01:01",
                        "dataDeValidade": "01/12/2100 23:59:59",
                        "desconto": 10.0,
                        "descricao": "Incrível, todos nosso produtos com R$10,00 reais de desconto!!!"
                    }
                ]
            },
            {
                "id": 1,
                "nome": "Headset Gamer",
                "dataDeCriacao": "22/11/2030 07:06:45",
                "dataDeValidade": "22/11/2030 07:06:45",
                "valor": 249.9,
                "descricao": "Headset Gamer HyperX Cloud Stinger, Drivers 50mm, Múltiplas Plataformas, P2 e P3 - HX-HSCS-BK/NA",
                "ofertas": [
                    {
                        "id": 2,
                        "nome": "Promoção de Natal",
                        "dataDeCriacao": "20/01/2010 00:01:01",
                        "dataDeValidade": "31/12/2022 23:59:59",
                        "desconto": 20.0,
                        "descricao": "Incrível, todos nosso produtos com R$20,00 reais de desconto!!!"
                    },
                    {
                        "id": 1,
                        "nome": "Queima de Estoque",
                        "dataDeCriacao": "30/12/2017 00:01:01",
                        "dataDeValidade": "01/12/2100 23:59:59",
                        "desconto": 10.0,
                        "descricao": "Incrível, todos nosso produtos com R$10,00 reais de desconto!!!"
                    }
                ]
            },
            {
                "id": 2,
                "nome": "HD SSD",
                "dataDeCriacao": "13/01/2017 06:10:13",
                "dataDeValidade": "13/01/2017 06:10:13",
                "valor": 179.9,
                "descricao": "SSD Kingston A400, 240GB, SATA, Leitura 500MB/s, Gravação 350MB/s - SA400S37/240G",
                "ofertas": []
            }
        ],
        "total": 2000.0
    }


#### PATCH

Atualiza parcialmente um Pedido pelo Id.

Exemplo:

    api/pedido/1
    
    {
      "cpf": "168.329.311-82",
      "total": 500.00
    }
    
Caso a atualização seja bem sucedida a API retornará uma mensagem: Pedido atualizado com sucesso! E o Status 200 OK.

Caso mal sucedida retornará o Status 400 Bad Request, assim como um objeto especificando o erro para o usuário:

    {
      "timestamp": "2022-07-31T21:28:11.639+00:00",
      "status": 400,
      "error": "Bad Request",
      "path": "/api/pedido/1"
    }

#### DELETE

Remove um Pedido pelo Id.

ex: api/pedido/42

Retorna 200 ok.

### /api/item/
Endpoint manipular os Itens do Pedido:

#### PATCH
Atualiza parcialmente um Item pelo Id.

Exemplo:

    api/item/2
    
    {
      "nome": "Headset Gamer",
      "dataDeCriacao": "22/11/2019 07:06:45",
      "valor": 249.90,
      "descricao": "Headset Gamer HyperX Cloud Stinger, Drivers 50mm, Múltiplas Plataformas, P2 e P3 - HX-HSCS-BK/NA"
    }
    
Caso a atualização seja bem sucedida a API retornará uma mensagem: Pedido atualizado com sucesso! E o Status 200 OK.

Caso mal sucedida retornará o Status 400 Bad Request, assim como um objeto especificando o erro para o usuário:

    {
      "timestamp": "2022-07-31T21:32:27.615+00:00",
      "status": 400,
      "error": "Bad Request",
      "path": "/api/item/2"
    }


## Arquivos Extras

Coleção de Testes de Requisições e Respostas da API utilizando o Postman.

    src/main/resources/postman/CompassAvaliaçãoSprint5_task1.postman_collection.json
    
Profile de testes no H2 utilizado para o desenvolvimento:

    src/main/resources/application-test.properties


