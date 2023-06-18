package net.darkhax.deathknell;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.security.SecureRandom;
import java.util.Random;

public class Constants {

    public static final String MOD_ID = "deathknell";
    public static final String MOD_NAME = "DeathKnell";
    public static final Logger LOG = LogManager.getLogger(MOD_NAME);
    public static final Random RAND = new SecureRandom();
}