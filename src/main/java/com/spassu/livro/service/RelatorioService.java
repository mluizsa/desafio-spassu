package com.spassu.livro.service;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import javax.management.JMRuntimeException;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@Service
public class RelatorioService {

    public byte[] gerarRelatorioLivros(List<?> lista, Map<String, Object> parametros, String nomeArquivo) throws JMRuntimeException, IOException {
        if (lista == null || lista.isEmpty()) {
            throw new IllegalArgumentException("A lista de livros está vazia ou nula.");
        }

        File file = ResourceUtils.getFile("classpath:reports/report_livros.jasper");

        if (!file.exists()) {
            throw new IOException("Arquivo de relatório não encontrado: " + file.getAbsolutePath());
        }
        try {
            JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(lista);
            JasperPrint jasperPrint = JasperFillManager.fillReport(file.getAbsolutePath(), parametros, dataSource);
            return JasperExportManager.exportReportToPdf(jasperPrint);
        } catch (JRException e) {
            throw new RuntimeException("Erro ao gerar relatório: " + e.getMessage(), e); // Re-lança como RuntimeException
        }
    }

    public String gerarNomeArquivo() {
        LocalDate dataAtual = LocalDate.now();
        return String.format("%s_livros.pdf", dataAtual);
    }
}
