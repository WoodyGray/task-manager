-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Хост: 127.0.0.1:3306
-- Время создания: Окт 15 2023 г., 22:26
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
-- Структура таблицы `personal_subtasks`
--

CREATE TABLE `personal_subtasks` (
  `id` int NOT NULL,
  `task_name` varchar(100) NOT NULL,
  `description` text,
  `deadline` date DEFAULT NULL,
  `status` int NOT NULL,
  `id_personal_task` int NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

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
  `status` int NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Дамп данных таблицы `personal_tasks`
--

INSERT INTO `personal_tasks` (`id`, `task_name`, `description`, `deadline`, `id_user`, `status`) VALUES
(2, 'have a party', NULL, NULL, 3, 0),
(3, 'repair the car', NULL, NULL, 2, 0);

-- --------------------------------------------------------

--
-- Структура таблицы `public_subtasks`
--

CREATE TABLE `public_subtasks` (
  `id` int NOT NULL,
  `task_name` varchar(100) NOT NULL,
  `description` text,
  `deadline` date DEFAULT NULL,
  `status` int NOT NULL,
  `id_public_task` int NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Дамп данных таблицы `public_subtasks`
--

INSERT INTO `public_subtasks` (`id`, `task_name`, `description`, `deadline`, `status`, `id_public_task`) VALUES
(1, 'extract phenylacetic acid', NULL, NULL, 0, 1),
(2, 'extract cooking flask', NULL, NULL, 0, 1);

-- --------------------------------------------------------

--
-- Структура таблицы `public_tasks`
--

CREATE TABLE `public_tasks` (
  `id` int NOT NULL,
  `task_name` varchar(100) NOT NULL,
  `description` text,
  `deadline` date DEFAULT NULL,
  `status` int NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Дамп данных таблицы `public_tasks`
--

INSERT INTO `public_tasks` (`id`, `task_name`, `description`, `deadline`, `status`) VALUES
(1, 'Gustavo\'s party №1', 'cook of 30 kg meth', NULL, 0);

-- --------------------------------------------------------

--
-- Структура таблицы `users`
--

CREATE TABLE `users` (
  `id` int NOT NULL,
  `login` varchar(100) NOT NULL,
  `password` varchar(100) NOT NULL,
  `full_name` varchar(100) NOT NULL,
  `email` varchar(70) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Дамп данных таблицы `users`
--

INSERT INTO `users` (`id`, `login`, `password`, `full_name`, `email`) VALUES
(1, 'woody', 'woody', 'Woody White', 'woodywhite@mail.ru'),
(2, 'whalter', 'whalter', 'Whalter White', 'whalterwhite@mail.ru'),
(3, 'jessee', 'jessee', 'Jessee Pinkman', 'jesseepinkman@mail.ru');

-- --------------------------------------------------------

--
-- Структура таблицы `users_and_public_subtasks`
--

CREATE TABLE `users_and_public_subtasks` (
  `id_user` int NOT NULL,
  `id_public_subtask` int NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Дамп данных таблицы `users_and_public_subtasks`
--

INSERT INTO `users_and_public_subtasks` (`id_user`, `id_public_subtask`) VALUES
(3, 1),
(2, 2);

-- --------------------------------------------------------

--
-- Структура таблицы `users_and_public_tasks`
--

CREATE TABLE `users_and_public_tasks` (
  `id_user` int NOT NULL,
  `id_public_task` int NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Дамп данных таблицы `users_and_public_tasks`
--

INSERT INTO `users_and_public_tasks` (`id_user`, `id_public_task`) VALUES
(2, 1),
(3, 1);

--
-- Индексы сохранённых таблиц
--

--
-- Индексы таблицы `personal_subtasks`
--
ALTER TABLE `personal_subtasks`
  ADD PRIMARY KEY (`id`),
  ADD KEY `id_personal_task` (`id_personal_task`);

--
-- Индексы таблицы `personal_tasks`
--
ALTER TABLE `personal_tasks`
  ADD PRIMARY KEY (`id`),
  ADD KEY `user_id` (`id_user`);

--
-- Индексы таблицы `public_subtasks`
--
ALTER TABLE `public_subtasks`
  ADD PRIMARY KEY (`id`),
  ADD KEY `id_public_task` (`id_public_task`);

--
-- Индексы таблицы `public_tasks`
--
ALTER TABLE `public_tasks`
  ADD PRIMARY KEY (`id`);

--
-- Индексы таблицы `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`id`);

--
-- Индексы таблицы `users_and_public_subtasks`
--
ALTER TABLE `users_and_public_subtasks`
  ADD KEY `id_user` (`id_user`),
  ADD KEY `id_public_subtask` (`id_public_subtask`);

--
-- Индексы таблицы `users_and_public_tasks`
--
ALTER TABLE `users_and_public_tasks`
  ADD KEY `id_user` (`id_user`),
  ADD KEY `id_public_task` (`id_public_task`);

--
-- AUTO_INCREMENT для сохранённых таблиц
--

--
-- AUTO_INCREMENT для таблицы `personal_subtasks`
--
ALTER TABLE `personal_subtasks`
  MODIFY `id` int NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT для таблицы `personal_tasks`
--
ALTER TABLE `personal_tasks`
  MODIFY `id` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT для таблицы `public_subtasks`
--
ALTER TABLE `public_subtasks`
  MODIFY `id` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT для таблицы `public_tasks`
--
ALTER TABLE `public_tasks`
  MODIFY `id` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT для таблицы `users`
--
ALTER TABLE `users`
  MODIFY `id` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- Ограничения внешнего ключа сохраненных таблиц
--

--
-- Ограничения внешнего ключа таблицы `personal_subtasks`
--
ALTER TABLE `personal_subtasks`
  ADD CONSTRAINT `personal_subtasks_ibfk_1` FOREIGN KEY (`id_personal_task`) REFERENCES `personal_tasks` (`id`);

--
-- Ограничения внешнего ключа таблицы `personal_tasks`
--
ALTER TABLE `personal_tasks`
  ADD CONSTRAINT `personal_tasks_ibfk_1` FOREIGN KEY (`id_user`) REFERENCES `users` (`id`);

--
-- Ограничения внешнего ключа таблицы `public_subtasks`
--
ALTER TABLE `public_subtasks`
  ADD CONSTRAINT `public_subtasks_ibfk_1` FOREIGN KEY (`id_public_task`) REFERENCES `public_tasks` (`id`);

--
-- Ограничения внешнего ключа таблицы `users_and_public_subtasks`
--
ALTER TABLE `users_and_public_subtasks`
  ADD CONSTRAINT `users_and_public_subtasks_ibfk_1` FOREIGN KEY (`id_user`) REFERENCES `users` (`id`),
  ADD CONSTRAINT `users_and_public_subtasks_ibfk_2` FOREIGN KEY (`id_public_subtask`) REFERENCES `public_subtasks` (`id`);

--
-- Ограничения внешнего ключа таблицы `users_and_public_tasks`
--
ALTER TABLE `users_and_public_tasks`
  ADD CONSTRAINT `users_and_public_tasks_ibfk_1` FOREIGN KEY (`id_user`) REFERENCES `users` (`id`),
  ADD CONSTRAINT `users_and_public_tasks_ibfk_2` FOREIGN KEY (`id_public_task`) REFERENCES `public_tasks` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
