-- src/main/resources/data.sql

-- 1) affectation
INSERT INTO `affectation` (`id`, `name`) VALUES (NULL, 'drm');
INSERT INTO `affectation` (`id`, `name`) VALUES (NULL, 'dsi');

-- 2) employe
INSERT INTO `employe`
  (`matricule`, `date_dentree`, `email`, `lastname`, `motdepasse`,
   `name`, `id_affectation`, `id_fonction`, `role_id`)
VALUES
  (NULL, '2025-05-19', 'menaili.haroun@gmail.com', 'menaili',
   'testtest23', 'haroun', '1', '1', '1');

-- 3) exercice
INSERT INTO `exercice` (`idexercice`, `label`)
  VALUES (NULL, '2024');

-- 4) fonction
INSERT INTO `fonction` (`id`, `name`)
  VALUES (NULL, 'ingenieur');

-- 5) role
INSERT INTO `role` (`id`, `label`) VALUES (NULL, 'admin');
INSERT INTO `role` (`id`, `label`) VALUES (NULL, 'employe');
INSERT INTO `role` (`id`, `label`) VALUES (NULL, 'responsable_ah');
INSERT INTO `role` (`id`, `label`) VALUES (NULL, 'sous_directeur');

-- 6) typedeconge
INSERT INTO `typedeconge` (`id`, `nameofleave`)
  VALUES (NULL, 'annuels');
