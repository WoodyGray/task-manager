-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Хост: 127.0.0.1:3306
-- Время создания: Окт 13 2023 г., 11:35
-- Версия сервера: 8.0.30
-- Версия PHP: 7.2.34

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- База данных: `task_manager`
--

-- --------------------------------------------------------

--
-- Структура таблицы `personal_tasks`
--

CREATE TABLE `personal_tasks` (
  `id` int NOT NULL,
  `task_name` varchar(100) NOT NULL,
  `description` text,
  `deadline` date DEFAULT NULL,
  `id_user` int NOT NULL,
  `id_subtasks` text
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Дамп данных таблицы `personal_tasks`
--

INSERT INTO `personal_tasks` (`id`, `task_name`, `description`, `deadline`, `id_user`, `id_subtasks`) VALUES
(1, 'cook diner', NULL, NULL, 1, NULL),
(2, 'have a party', NULL, NULL, 3, NULL),
(3, 'repair the car', NULL, NULL, 2, NULL);

-- --------------------------------------------------------

--
-- Структура таблицы `public_tasks`
--

CREATE TABLE `public_tasks` (
  `id` int NOT NULL,
  `task_name` varchar(100) NOT NULL,
  `description` text,
  `id_users` text NOT NULL,
  `deadline` date DEFAULT NULL,
  `id_subtasks` text
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Дамп данных таблицы `public_tasks`
--

INSERT INTO `public_tasks` (`id`, `task_name`, `description`, `id_users`, `deadline`, `id_subtasks`) VALUES
(1, 'Gustavo\'s party №1', 'cook of 30 kg meth', '2,3', NULL, '1,2');

-- --------------------------------------------------------

--
-- Структура таблицы `subtasks`
--

CREATE TABLE `subtasks` (
  `id` int NOT NULL,
  `task_name` varchar(100) NOT NULL,
  `description` text,
  `deadline` date DEFAULT NULL,
  `users` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Дамп данных таблицы `subtasks`
--

INSERT INTO `subtasks` (`id`, `task_name`, `description`, `deadline`, `users`) VALUES
(1, 'extract phenylacetic acid', NULL, NULL, '3'),
(2, 'extract cooking flask', NULL, NULL, '2');

-- --------------------------------------------------------

--
-- Структура таблицы `users`
--

CREATE TABLE `users` (
  `id` int NOT NULL,
  `login` varchar(100) NOT NULL,
  `password` varchar(100) NOT NULL,
  `full_name` varchar(100) NOT NULL,
  `email` varchar(70) NOT NULL,
  `personalTasks` text,
  `publicTasks` text
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Дамп данных таблицы `users`
--

INSERT INTO `users` (`id`, `login`, `password`, `full_name`, `email`, `personalTasks`, `publicTasks`) VALUES
(1, 'woody', 'woody', 'Woody White', 'woodywhite@mail.ru', '1', NULL),
(2, 'whalter', 'whalter', 'Whalter White', 'whalterwhite@mail.ru', '3', '1'),
(3, 'jessee', 'jessee', 'Jessee Pinkman', 'jesseepinkman@mail.ru', '2', '1');

--
-- Индексы сохранённых таблиц
--

--
-- Индексы таблицы `personal_tasks`
--
ALTER TABLE `personal_tasks`
  ADD PRIMARY KEY (`id`),
  ADD KEY `user_id` (`id_user`);

--
-- Индексы таблицы `public_tasks`
--
ALTER TABLE `public_tasks`
  ADD PRIMARY KEY (`id`);

--
-- Индексы таблицы `subtasks`
--
ALTER TABLE `subtasks`
  ADD PRIMARY KEY (`id`);

--
-- Индексы таблицы `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT для сохранённых таблиц
--

--
-- AUTO_INCREMENT для таблицы `personal_tasks`
--
ALTER TABLE `personal_tasks`
  MODIFY `id` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT для таблицы `public_tasks`
--
ALTER TABLE `public_tasks`
  MODIFY `id` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT для таблицы `subtasks`
--
ALTER TABLE `subtasks`
  MODIFY `id` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT для таблицы `users`
--
ALTER TABLE `users`
  MODIFY `id` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- Ограничения внешнего ключа сохраненных таблиц
--

--
-- Ограничения внешнего ключа таблицы `personal_tasks`
--
ALTER TABLE `personal_tasks`
  ADD CONSTRAINT `personal_tasks_ibfk_1` FOREIGN KEY (`id_user`) REFERENCES `users` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
