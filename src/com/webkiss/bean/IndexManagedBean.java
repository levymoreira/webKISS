package com.webkiss.bean;

import java.io.ByteArrayInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.CroppedImage;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

@ManagedBean
@SessionScoped
public class IndexManagedBean {

	private StreamedContent imagemEnviada = new DefaultStreamedContent();
	private String imagemTemporaria;
	private CroppedImage croppedImage;
	private boolean exibeBotao = false;
	private List<String> images  = new ArrayList<String>();

	//Criar configuracao do sistema 
    private String pastaUpload = "C:\\Users\\levymoreira\\git\\webKISS\\WebContent\\upload\\";	

	public void criaArquivo(byte[] bytes, String arquivo) {	
		FileOutputStream fos;
		try {
			fos = new FileOutputStream(arquivo);
			fos.write(bytes);
			fos.close();
		} catch (FileNotFoundException ex) {
			Logger.getLogger(IndexManagedBean.class.getName()).log(
					Level.SEVERE, null, ex);
		} catch (IOException ex) {
			Logger.getLogger(IndexManagedBean.class.getName()).log(
					Level.SEVERE, null, ex);
		}
	}

	public void enviarImagem(FileUploadEvent event) {
		byte[] img = event.getFile().getContents();
		imagemTemporaria = event.getFile().getFileName();
		String arquivo = pastaUpload + imagemTemporaria ; 
		System.out.println(arquivo);
		criaArquivo(img, arquivo);
		setExibeBotao(true);
		System.out.println(imagemTemporaria);
		images.add(imagemTemporaria);
		for (String s : images) {
			System.out.println(s);
			
		}
	}


	public StreamedContent getImagemEnviada() {
		return imagemEnviada;
	}

	public void setImagemEnviada(StreamedContent imagemEnviada) {
		this.imagemEnviada = imagemEnviada;
	}

	public String getImagemTemporaria() {
		return imagemTemporaria;
	}

	public void setImagemTemporaria(String imagemTemporaria) {
		this.imagemTemporaria = imagemTemporaria;
	}

	public boolean isExibeBotao() {
		return exibeBotao;
	}

	public void setExibeBotao(boolean exibeBotao) {
		this.exibeBotao = exibeBotao;
	}

	public String getPastaUpload() {
		return pastaUpload;
	}

	public void setPastaUpload(String pastaUpload) {
		this.pastaUpload = pastaUpload;
	}

	public CroppedImage getCroppedImage() {
		return croppedImage;
	}

	public void setCroppedImage(CroppedImage croppedImage) {
		this.croppedImage = croppedImage;
	}

	public List<String> getImages() {
		return images;
	}

	public void setImages(List<String> images) {
		this.images = images;
	}
	

	
	

}