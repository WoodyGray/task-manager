-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Хост: 127.0.0.1:3306
-- Время создания: Дек 11 2023 г., 15:27
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
  `deadline` date NOT NULL,
  `status` int NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Дамп данных таблицы `public_tasks`
--

INSERT INTO `public_tasks` (`id`, `task_name`, `description`, `deadline`, `status`) VALUES
(1, 'Gustavo\'s party №1', 'cook of 30 kg meth', '2021-04-03', 2),
(2, 'test task', 'description', '2020-11-19', 2);

-- --------------------------------------------------------

--
-- Структура таблицы `roles`
--

CREATE TABLE `roles` (
  `id` int NOT NULL,
  `name` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Дамп данных таблицы `roles`
--

INSERT INTO `roles` (`id`, `name`) VALUES
(1, 'ROLE_ADMIN'),
(2, 'ROLE_USER');

-- --------------------------------------------------------

--
-- Структура таблицы `subscriptions`
--

CREATE TABLE `subscriptions` (
  `endpoint` varchar(300) NOT NULL,
  `id_user` int NOT NULL,
  `p256dh` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `auth` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Дамп данных таблицы `subscriptions`
--

INSERT INTO `subscriptions` (`endpoint`, `id_user`, `p256dh`, `auth`) VALUES
('https://fcm.googleapis.com/fcm/send/d1QsrdI4YsE:APA91bEG5moWuvnc4aTStL4NXkieMMMdhy2TogHFovMGvYNjulU_bkmGI9Ny5DfzBJMzfRkHpX1-PwOUSvsF1_m6PUe8k_feyvy5beZ5PIm2lqN7jS1p9cDKvPEf_KhPLtX1dFO-hvv-', 2, 'BC6LWpH9R8sOLV0kjz0TW_xKXZ7WxiXIJDkW7CYChratj6ge6-OQvBC19_Y-MYQ0DTIJx8-hSRTQlEQc4GRyRpQ', 'gXJ62g3eYgKHyM_3uJAI5w'),
('https://fcm.googleapis.com/fcm/send/fQktka9qGts:APA91bFhIwG_12o7ry30ew26zQDjMezzNSRpHiuzHiSycNeIxO7BC-cFnjwVvOENTPF2YDD5w1rdfAoULOj3EV_QdWOd8Zszq261SO58MglZfr0Mt0p_VikDLJXy8KyJCFyeI2WTM8V6', 2, 'BFVmeFV-mzUorDsj1WjUCLo8AW6Dv0u12JbfuqpHT1uQazJLU8RDKDuqHwZLdVtLFUTKPdQmhbY-mWUMUsXXklw', '9YXycS7qoN0w0wL7y6VShg');

-- --------------------------------------------------------

--
-- Структура таблицы `users`
--

CREATE TABLE `users` (
  `id` int NOT NULL,
  `username` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `password` varchar(100) NOT NULL,
  `full_name` varchar(100) NOT NULL,
  `email` varchar(70) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Дамп данных таблицы `users`
--

INSERT INTO `users` (`id`, `username`, `password`, `full_name`, `email`) VALUES
(1, 'woody', '$2a$10$mlzfOU/RU5nmg6JaK.sE4ehCQnenWlHaKahLX6nOXX1Lvj06cXcbO', 'Woody White', 'woodywhite@mail.ru'),
(2, 'whalter', '$2a$10$DyFqpudbD.g37Io4.0zRK.eml7V02xf1dW6nARe1mIwlAEFDYNLCe', 'Whalter White', 'whalterwhite@mail.ru'),
(3, 'jessee', '$2a$10$b51werAfcRvElXScuPcIMueTmOptE/9KXgDl9YDJ55faTvUxayd3K', 'Jessee Pinkman', 'jesseepinkman@mail.ru'),
(5, 'gustavo', '$2a$10$LdMBkzeKeZGJl9RF88THQuq0mACnqSavCpNThui3XSxvnE4iOgcaG', 'Gus', 'woody@mail.ru'),
(6, 'thewoody', '$2a$10$BcWmImQBLQuPFrP91nQRA.JLonlGrqGBSH27IeUnSDucJd.j6w/ky', 'woody white', 'w@mail.com');

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
(5, 1),
(2, 2);

-- --------------------------------------------------------

--
-- Структура таблицы `users_and_roles`
--

CREATE TABLE `users_and_roles` (
  `id_user` int NOT NULL,
  `id_role` int NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Дамп данных таблицы `users_and_roles`
--

INSERT INTO `users_and_roles` (`id_user`, `id_role`) VALUES
(1, 1),
(2, 2),
(3, 2),
(5, 2),
(6, 2);

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
-- Индексы таблицы `roles`
--
ALTER TABLE `roles`
  ADD PRIMARY KEY (`id`);

--
-- Индексы таблицы `subscriptions`
--
ALTER TABLE `subscriptions`
  ADD PRIMARY KEY (`endpoint`),
  ADD KEY `id_user` (`id_user`);

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
-- Индексы таблицы `users_and_roles`
--
ALTER TABLE `users_and_roles`
  ADD KEY `id_user` (`id_user`),
  ADD KEY `id_role` (`id_role`);

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
  MODIFY `id` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT для таблицы `roles`
--
ALTER TABLE `roles`
  MODIFY `id` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT для таблицы `users`
--
ALTER TABLE `users`
  MODIFY `id` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

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
-- Ограничения внешнего ключа таблицы `subscriptions`
--
ALTER TABLE `subscriptions`
  ADD CONSTRAINT `subscriptions_ibfk_1` FOREIGN KEY (`id_user`) REFERENCES `users` (`id`);

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

--
-- Ограничения внешнего ключа таблицы `users_and_roles`
--
ALTER TABLE `users_and_roles`
  ADD CONSTRAINT `users_and_roles_ibfk_1` FOREIGN KEY (`id_role`) REFERENCES `roles` (`id`),
  ADD CONSTRAINT `users_and_roles_ibfk_2` FOREIGN KEY (`id_user`) REFERENCES `users` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
