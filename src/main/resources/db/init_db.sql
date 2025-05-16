CREATE TABLE Utilisateur (
                             id INTEGER PRIMARY KEY AUTOINCREMENT,
                             nom TEXT NOT NULL,
                             prenom TEXT NOT NULL,
                             email TEXT UNIQUE NOT NULL,
                             mot_de_passe TEXT NOT NULL,
                             type TEXT CHECK(type IN ('Etudiant', 'Enseignant', 'Administrateur')) NOT NULL
);

CREATE TABLE Notification (
                              id INTEGER PRIMARY KEY AUTOINCREMENT,
                              contenu TEXT NOT NULL,
                              date_envoi DATETIME NOT NULL,
                              expediteur_id INTEGER NOT NULL,
                              destinataire_id INTEGER NOT NULL,
                              FOREIGN KEY (expediteur_id) REFERENCES Utilisateur(id),
                              FOREIGN KEY (destinataire_id) REFERENCES Utilisateur(id)
);

CREATE TABLE Cours (
                       id INTEGER PRIMARY KEY AUTOINCREMENT,
                       matiere TEXT NOT NULL,
                       id_enseignant INTEGER NOT NULL,
                       id_salle INTEGER NOT NULL,
                       date_debut DATETIME NOT NULL,
                       duree INTEGER NOT NULL,
                       FOREIGN KEY (id_enseignant) REFERENCES Utilisateur(id),
                       FOREIGN KEY (id_salle) REFERENCES Salle(id)
);

CREATE TABLE EmploiDuTemps (
                                etudiant_id INTEGER NOT NULL,
                                cours_id INTEGER NOT NULL,
                                PRIMARY KEY (etudiant_id, cours_id),
                                FOREIGN KEY (etudiant_id) REFERENCES Utilisateur(id),
                                FOREIGN KEY (cours_id) REFERENCES Cours(id)
);

CREATE TABLE Horaire (
                         id INTEGER PRIMARY KEY AUTOINCREMENT,
                         date_debut DATETIME NOT NULL,
                         date_fin DATETIME NOT NULL,
                         cours_id INTEGER NOT NULL,
                         salle_id INTEGER NOT NULL,
                         FOREIGN KEY (cours_id) REFERENCES Cours(id),
                         FOREIGN KEY (salle_id) REFERENCES Salle(id)
);

CREATE TABLE Salle (
                       id INTEGER PRIMARY KEY AUTOINCREMENT,
                       nom TEXT NOT NULL,
                       capacite INTEGER
);