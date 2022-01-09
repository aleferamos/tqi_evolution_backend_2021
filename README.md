# tqi_evolution_backend_2021
Desafio TQI - análise de crédito

Linguagem
<img src="https://svgshare.com/i/dPV.svg" width="50px">

FrameWork
<img src="https://svgshare.com/i/dN3.svg" width="35px">

Banco de dados
<img src="https://svgshare.com/i/dNy.svg" width="35px">
______________________________________________________________________________________________________________________________________________________________
<h3>Um pouco sobre a aplicação :technologist:</h3>

A aplicação uma api rest e é separada em camadas, sendo elas: model, repository, service, controller.
onde model é a camada de modelo, onde é definido o nome do objeto e seus atributos. A camada repository é onde fica os métodos de persistência, e que por si já vem com métodos para poder facilitar a persistência. A camada service é a camada onde fica as regras de negócios ou os métodos de serviço, e a camada final que pode ser criado os end-points para fazer as requisições.

<h4>Contexto</h4>
Foi criado uma solução para uma empresa de empréstimos e precisa de um sistema de análise de crédito cuja as funções são: <br>
Cadastro de cliente :pouting_face:<br>
Autenticação :key:<br>
Solicitação de empréstimo :man_dancing:<br>
Acompanhamento das solicitações de empréstimo :iphone:<br> 
Detalhar o emprestimo :mag:
<h5>Excessões</h5>
O mesmo apresenta algumas excessões :stop_sign:, sendo elas: <br>
Na parte de cadastro de cliente o email a ser cadastrado não pode já constar no banco de dados <br>
Na parte de do cadastro do emprestimo, o maximo de quantidade de parcelas é 60, e a data da cobrança da primeira parcela pode ser no maximo até 3 meses
