-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Tempo de geração: 16/02/2024 às 00:30
-- Versão do servidor: 10.4.28-MariaDB
-- Versão do PHP: 8.2.4

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Banco de dados: `tc`
--

-- --------------------------------------------------------

--
-- Estrutura para tabela `aluno`
--

CREATE TABLE `aluno` (
  `id` int(11) NOT NULL,
  `nome` varchar(100) NOT NULL,
  `rg` varchar(15) DEFAULT NULL,
  `cpf` varchar(15) NOT NULL,
  `datacadastro` date NOT NULL DEFAULT current_timestamp(),
  `idade` int(2) NOT NULL,
  `datanascimento` date NOT NULL,
  `matricula` int(100) NOT NULL,
  `idresponsavel` int(100) NOT NULL,
  `observacoes` varchar(200) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Despejando dados para a tabela `aluno`
--

INSERT INTO `aluno` (`id`, `nome`, `rg`, `cpf`, `datacadastro`, `idade`, `datanascimento`, `matricula`, `idresponsavel`, `observacoes`) VALUES
(36, 'Silas Rodrigues', '', '539.715.848-88', '2024-02-09', 1, '2022-04-10', 1847979220, 4, 'nenhuma'),
(37, 'Caique', '', '626.499.230-59', '2024-02-09', 2, '2021-03-10', 2123249410, 3, 'nenhuma'),
(38, 'Luana Pinheiro', '', '280.087.100-80', '2024-02-09', 3, '2020-03-10', 1814314719, 2, ''),
(39, 'Lukas pinheiro', '', '581.822.837-10', '2024-02-09', 4, '2019-03-10', 1749122599, 4, ''),
(40, 'Jesiel ribeiro', '', '163.136.565-71', '2024-02-09', 1, '2022-03-10', 1851227934, 3, ''),
(41, 'João Pires', '', '041.883.437-70', '2024-02-09', 1, '2022-05-10', 1996793502, 4, 'Da novela do sebetê');

-- --------------------------------------------------------

--
-- Estrutura para tabela `gastos`
--

CREATE TABLE `gastos` (
  `id` int(11) NOT NULL,
  `nomeproduto` varchar(25) NOT NULL,
  `valorpago` double(11,2) NOT NULL,
  `datacompra` date NOT NULL,
  `formapagamento` varchar(25) NOT NULL,
  `parcelas` varchar(25) NOT NULL,
  `quantidade` int(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Despejando dados para a tabela `gastos`
--

INSERT INTO `gastos` (`id`, `nomeproduto`, `valorpago`, `datacompra`, `formapagamento`, `parcelas`, `quantidade`) VALUES
(12, 'Canetão', 500.00, '2026-11-29', 'Crédito', '12x', 250),
(13, 'Faixa de Judô', 200.00, '2025-03-10', 'Crédito', '1x', 2),
(14, 'Giz', 200.00, '2024-03-10', 'Pix', '1x', 150),
(16, 'Mesa', 250.00, '2025-03-10', 'Débito', '1x', 25);

-- --------------------------------------------------------

--
-- Estrutura para tabela `pagamentos`
--

CREATE TABLE `pagamentos` (
  `id` int(11) NOT NULL,
  `datapagamento` date NOT NULL,
  `valorpago` double(11,2) NOT NULL,
  `mesreferencia` varchar(10) NOT NULL,
  `anoreferencia` int(4) NOT NULL,
  `formadepagamento` varchar(20) NOT NULL,
  `nomealuno` varchar(50) NOT NULL,
  `nometurma` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Despejando dados para a tabela `pagamentos`
--

INSERT INTO `pagamentos` (`id`, `datapagamento`, `valorpago`, `mesreferencia`, `anoreferencia`, `formadepagamento`, `nomealuno`, `nometurma`) VALUES
(22, '2024-02-10', 250.00, 'Maio', 2024, 'Pix', 'Caique', 'Inglês'),
(23, '2023-02-28', 200.00, 'Março', 2025, 'Dinheiro', 'João Pires', 'turma'),
(24, '2024-12-30', 250.00, 'Junho', 2024, 'Débito', 'Luana Pinheiro', 'Balé'),
(25, '2022-03-10', 350.00, 'Janeiro', 2024, 'Crédito', 'João Pires', 'turma'),
(26, '2025-02-20', 150.00, 'Janeiro', 2025, 'Crédito', 'Silas Rodrigues', 'Inglês');

-- --------------------------------------------------------

--
-- Estrutura para tabela `responsavel`
--

CREATE TABLE `responsavel` (
  `id` int(11) NOT NULL,
  `nome` varchar(100) NOT NULL,
  `telefone` varchar(20) NOT NULL,
  `rg` varchar(12) DEFAULT NULL,
  `cpf` varchar(15) NOT NULL,
  `cep` int(8) NOT NULL,
  `rua` varchar(100) NOT NULL,
  `numero` int(10) NOT NULL,
  `cidade` varchar(100) NOT NULL,
  `estado` varchar(100) NOT NULL,
  `genero` varchar(9) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Despejando dados para a tabela `responsavel`
--

INSERT INTO `responsavel` (`id`, `nome`, `telefone`, `rg`, `cpf`, `cep`, `rua`, `numero`, `cidade`, `estado`, `genero`) VALUES
(2, 'Regisnaldo', '(44) 8444-9986', '15.475.120-3', '108.308.109-86', 87538000, 'projetada a', 0, 'Perobal', 'Selecione', 'Masculino'),
(3, 'Maria ', '(44) 8444-9986', '15.475.120-3', '108.308.109-86', 87538000, 'teste', 0, 'perobal', 'São Paulo', 'Feminino'),
(4, 'Borth', '(44) 4444-4444', '', '107.792.621-90', 87538000, 'teste', 0, 'Umuarama', 'São Paulo', 'Masculino');

-- --------------------------------------------------------

--
-- Estrutura para tabela `responsavel_aluno`
--

CREATE TABLE `responsavel_aluno` (
  `id` int(11) NOT NULL,
  `idaluno` int(11) NOT NULL,
  `idresponsavel` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Despejando dados para a tabela `responsavel_aluno`
--

INSERT INTO `responsavel_aluno` (`id`, `idaluno`, `idresponsavel`) VALUES
(6, 10, 2),
(7, 12, 2),
(9, 12, 3);

-- --------------------------------------------------------

--
-- Estrutura para tabela `turma`
--

CREATE TABLE `turma` (
  `id` int(11) NOT NULL,
  `nometurma` varchar(100) NOT NULL,
  `quantidadematriculados` int(10) NOT NULL,
  `atividades` varchar(100) NOT NULL,
  `valormensalidade` double(11,2) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Despejando dados para a tabela `turma`
--

INSERT INTO `turma` (`id`, `nometurma`, `quantidadematriculados`, `atividades`, `valormensalidade`) VALUES
(1, 'judo', 1, 'judo', 100.00),
(5, 'Inglês', 2, 'Aulas', 100.00),
(6, 'Balé', 2, 'aulas', 100.00),
(7, 'turma', 1, 'aulas', 200.00);

-- --------------------------------------------------------

--
-- Estrutura para tabela `turmaporaluno`
--

CREATE TABLE `turmaporaluno` (
  `id` int(11) NOT NULL,
  `datainicio` date NOT NULL,
  `datafechamento` date NOT NULL,
  `idturma` int(10) NOT NULL,
  `idaluno` int(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Despejando dados para a tabela `turmaporaluno`
--

INSERT INTO `turmaporaluno` (`id`, `datainicio`, `datafechamento`, `idturma`, `idaluno`) VALUES
(39, '2024-03-10', '2025-03-10', 5, 37),
(40, '2024-03-10', '2025-03-10', 6, 37),
(41, '2024-03-10', '2025-03-10', 7, 41),
(42, '2024-12-10', '2025-02-10', 6, 38),
(43, '2024-12-10', '2026-12-10', 5, 36),
(44, '2025-02-10', '2026-02-10', 1, 36);

-- --------------------------------------------------------

--
-- Estrutura para tabela `usuario`
--

CREATE TABLE `usuario` (
  `id` int(11) NOT NULL,
  `nome` varchar(200) NOT NULL,
  `telefone` varchar(15) NOT NULL,
  `senha` varchar(100) NOT NULL,
  `rg` varchar(15) NOT NULL,
  `cpf` varchar(15) NOT NULL,
  `email` varchar(30) NOT NULL,
  `resposta1` varchar(50) NOT NULL,
  `resposta2` varchar(50) NOT NULL,
  `pergunta1` varchar(50) NOT NULL,
  `pergunta2` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Despejando dados para a tabela `usuario`
--

INSERT INTO `usuario` (`id`, `nome`, `telefone`, `senha`, `rg`, `cpf`, `email`, `resposta1`, `resposta2`, `pergunta1`, `pergunta2`) VALUES
(7, 'Salim', '(11) 4647-4591', 'a665a45920422f9d417e4867efdc4fb8a04a1f3fff1fa07e998e86f7f7a27ae3', '51.051.055-0', '108-308-109.86', 'usuario@gmail.com', 'Preto', 'frango', 'Qual sua Cor Favorita?', 'Qual sua Comida Favorita?'),
(10, 'salim2', '(44) 4444-4444', '03ac674216f3e15c761ee1a5e255f067953623c8b388b4459e13f978d7c846f4', '', '614-722-170.12', 'teste@teste.com', 'Verde', 'bife', 'Qual sua Cor Favorita?', 'Qual sua Comida Favorita?'),
(11, 'teste', '(44) 4444-4444', 'a665a45920422f9d417e4867efdc4fb8a04a1f3fff1fa07e998e86f7f7a27ae3', '', '941-269-670.10', 'teste@teste2.com', 'Azul', 'Bife', 'Qual sua cor favorita?', 'Qual sua comida favorita?');

--
-- Índices para tabelas despejadas
--

--
-- Índices de tabela `aluno`
--
ALTER TABLE `aluno`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `matricula` (`matricula`),
  ADD KEY `idresponsavel` (`idresponsavel`);

--
-- Índices de tabela `gastos`
--
ALTER TABLE `gastos`
  ADD PRIMARY KEY (`id`);

--
-- Índices de tabela `pagamentos`
--
ALTER TABLE `pagamentos`
  ADD PRIMARY KEY (`id`);

--
-- Índices de tabela `responsavel`
--
ALTER TABLE `responsavel`
  ADD PRIMARY KEY (`id`);

--
-- Índices de tabela `responsavel_aluno`
--
ALTER TABLE `responsavel_aluno`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FK_aluno_responsavel` (`idaluno`),
  ADD KEY `FK_responsavel_aluno` (`idresponsavel`);

--
-- Índices de tabela `turma`
--
ALTER TABLE `turma`
  ADD PRIMARY KEY (`id`);

--
-- Índices de tabela `turmaporaluno`
--
ALTER TABLE `turmaporaluno`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FK_turma` (`idturma`),
  ADD KEY `FK_aluno` (`idaluno`);

--
-- Índices de tabela `usuario`
--
ALTER TABLE `usuario`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT para tabelas despejadas
--

--
-- AUTO_INCREMENT de tabela `aluno`
--
ALTER TABLE `aluno`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=42;

--
-- AUTO_INCREMENT de tabela `gastos`
--
ALTER TABLE `gastos`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=17;

--
-- AUTO_INCREMENT de tabela `pagamentos`
--
ALTER TABLE `pagamentos`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=27;

--
-- AUTO_INCREMENT de tabela `responsavel`
--
ALTER TABLE `responsavel`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT de tabela `responsavel_aluno`
--
ALTER TABLE `responsavel_aluno`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=10;

--
-- AUTO_INCREMENT de tabela `turma`
--
ALTER TABLE `turma`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;

--
-- AUTO_INCREMENT de tabela `turmaporaluno`
--
ALTER TABLE `turmaporaluno`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=45;

--
-- AUTO_INCREMENT de tabela `usuario`
--
ALTER TABLE `usuario`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=12;

--
-- Restrições para tabelas despejadas
--

--
-- Restrições para tabelas `aluno`
--
ALTER TABLE `aluno`
  ADD CONSTRAINT `aluno_ibfk_2` FOREIGN KEY (`idresponsavel`) REFERENCES `responsavel` (`id`);

--
-- Restrições para tabelas `turmaporaluno`
--
ALTER TABLE `turmaporaluno`
  ADD CONSTRAINT `FK_aluno` FOREIGN KEY (`idaluno`) REFERENCES `aluno` (`id`),
  ADD CONSTRAINT `FK_turma` FOREIGN KEY (`idturma`) REFERENCES `turma` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
