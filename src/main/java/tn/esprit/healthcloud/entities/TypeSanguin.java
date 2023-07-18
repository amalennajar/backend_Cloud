package tn.esprit.healthcloud.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;


public enum TypeSanguin {
    A_POSITIF,
    A_NEGATIF,
    B_POSITIF,
    B_NEGATIF,
    AB_POSITIF,
    AB_NEGATIF,
    O_POSITIF,
    O_NEGATIF

}
