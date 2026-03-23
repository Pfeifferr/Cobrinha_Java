# 🐍 Jogo da Cobrinha (Snake Game) em Java

Este é um projeto clássico do jogo da cobrinha desenvolvido em **Java**, com o objetivo de praticar **lógica de programação**, **estrutura de dados simples** e **interface gráfica** utilizando **Swing** e **AWT**.

---

## 🎮 Como Jogar

O objetivo é simples: controlar a cobra para coletar a comida que aparece na tela.

- A cada comida coletada, a cobra aumenta de tamanho.
- O jogo termina quando:
  - A cobra colide com as bordas da janela
  - A cobra colide com o próprio corpo

### 🎯 Controles

- ⬆️ **Seta para Cima** → Move a cobra para cima  
- ⬇️ **Seta para Baixo** → Move a cobra para baixo  
- ⬅️ **Seta para Esquerda** → Move a cobra para a esquerda  
- ➡️ **Seta para Direita** → Move a cobra para a direita  

---

## 🛠️ Tecnologias Utilizadas

- **Linguagem:** Java  
- **Interface Gráfica:** Swing / AWT  
- **Paradigma:** Programação Orientada a Objetos (POO)  

---

## 🚀 Como Executar o Projeto

### Pré-requisitos

Certifique-se de ter o **JDK (Java Development Kit)** instalado em sua máquina.

### Instalação e Execução

```bash
git clone https://github.com/Pfeifferr/Cobrinha_Java.git
cd Cobrinha_Java
javac *.java
java GameFrame

## 📝 Estrutura do Código
O projeto está organizado da seguinte forma:

* **GameFrame.java**
    * Responsável por criar a janela principal (`JFrame`) que contém o jogo.
* **GamePanel.java**
    * Contém a lógica principal do jogo:
        * **Movimentação:** Atualização das coordenadas da cobra.
        * **Detecção de Colisão:** Verifica colisões com bordas e com o próprio corpo.
        * **Renderização:** Desenho da cobra e da comida utilizando `Graphics`.

## 👨‍💻 Autor
Desenvolvido por **Pfeifferr**
