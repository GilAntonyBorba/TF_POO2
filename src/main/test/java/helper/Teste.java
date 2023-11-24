package helper;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar; import java.util.List;

import javax.persistence.EntityManager;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import helper.*;

import Classes.*;

class Teste {
   @BeforAll
   public void inicia() {
      String poder = "Escudo";
      bool voa = true;
      Heroi hero, herotest;
      Vilao vilain, vilaintest;
   }

   @BeforeEach
   public void TesteDeConexao() {
       EntityManager em = HibernateManager.getEntityManager();

       try {
           em.getTransaction().begin();
       } finally {
           em.close();
       }
   }

   @Test
   public void registrarHeroiTeste() {
      String nome = "Capitão";
      Heroi hero = new Heroi(nome, poder, voa);
      HibernateController.registrarHeroi(hero);

      heroTest = HibernateController.procurarHeroi(nome);
      assertNotNull(heroTest);
      assertEquals(nome, heroTest.getNome());
      assertEquals(poder, heroTest.getPoder());
      bool podevoar = heroTest.getVoar() == ", poder voar";
      assertEquals(voa, podevoar);

   }

   @Test
   public void registraVilaoTeste() {
      String nome = "Thanos";
      vilain = new Vilao (nome, poder, voa);
      HibernateController.registrarVilao(hero);

      vilaintest = HibernateController.procurarVilao(nome);
      assertNotNull(vilaintest);
      assertEquals(nome, vilaintest.getNome());
      assertEquals(poder, herotest.getPoder());
      bool podevoar = herotest.getVoar() == ", poder voar";
      assertEquals(voa, podevoar);
   }

   @Test
   public void procurarHeroiTeste() {
      herotest = HibernateController.procurarHeroi("Capitao");
      assertNotNull(herotest);

      assertNull(HibernateController.procurarHeroi("Thanos"));
   }

   @Test
   public void procurarVilaoTeste() {
      vilaintest = HibernateController.procurarHeroi("Thanos");
      assertNotNull(vilaintest);

      assertNull(HibernateController.procurarHeroi("Capitao"));
   }

   @Test
   public void limparTabelaHeroiTeste() {
      HibernateController.limparTabelaHeroi();
      herotest = HibernateController.procurarHeroi("Capitão");

      assertNull(vilaintest);
   }

   @Test
   public void limparTabelaVilaoTeste() {
      HibernateController.limparTabelaVilao();
      vilaintest = HibernateController.procurarTest("Thanos");
      
      assertNull(vilaintest);
   }

   @AfterAll
   public static void tearDown() {
      
      try {
         em.getTransaction().begin();
         HibernateController.limparTabelaHeroi();
         HibernateController.limparTabelaVilao();

      } catch (Exception e) {
         e.printStackTrace();
      } finally {
         em.close();
      }
   }
} 
