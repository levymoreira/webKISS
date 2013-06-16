package com.webkiss.bean;

import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.PhaseId;

import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

@ManagedBean
@SessionScoped
public class IndexManagedBean {

	private StreamedContent imagemEnviada = new DefaultStreamedContent();
	private String imagemTemporaria;

	private List<String> images = new ArrayList<String>();
	
	public IndexManagedBean(){}
	
	// Criar configuracao do sistema, 
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

	public StreamedContent getImage() throws IOException {
		FacesContext context = FacesContext.getCurrentInstance();

		if (context.getCurrentPhaseId() == PhaseId.RENDER_RESPONSE) {			
			return new DefaultStreamedContent();
		} else {
			Map<String, String> param = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
			String id = param.get("id");	
			byte[] bytesImage = this.read(pastaUpload+id);
			StreamedContent streamImage = new DefaultStreamedContent(new ByteArrayInputStream(bytesImage));
			return streamImage;
		}
	}

	public void enviarImagem(FileUploadEvent event) {
		byte[] img = event.getFile().getContents();
		imagemTemporaria = event.getFile().getFileName();
		String arquivo = pastaUpload + imagemTemporaria;
		criaArquivo(img, arquivo);

		//depois colocar a ultima imagem que foi enviada ... 
		setImagemEnviada(new DefaultStreamedContent(new ByteArrayInputStream(img)));

		images.add(imagemTemporaria);//salvar em banco o nome da imagem mais nada, 
		//pra exibir todas imagens na galeria basta fazer um select no banco e retornar somente os nomes para a propriedade imagens
	}

	private byte[] read(String aInputFileName) {

		File file = new File(aInputFileName);

		byte[] result = new byte[(int) file.length()];
		try {
			InputStream input = null;
			try {
				int totalBytesRead = 0;
				input = new BufferedInputStream(new FileInputStream(file));
				while (totalBytesRead < result.length) {
					int bytesRemaining = result.length - totalBytesRead;
					int bytesRead = input.read(result, totalBytesRead,
							bytesRemaining);
					if (bytesRead > 0) {
						totalBytesRead = totalBytesRead + bytesRead;
					}
				}

			} finally {

				input.close();
			}
		} catch (FileNotFoundException ex) {

		} catch (IOException ex) {

		}
		return result;
	}

	public List<String> getImages() {
		return images;
	}

	public StreamedContent getImagemEnviada() {
		return imagemEnviada;
	}

	public void setImages(List<String> images) {
		this.images = images;
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

	public String getPastaUpload() {
		return pastaUpload;
	}

	public void setPastaUpload(String pastaUpload) {
		this.pastaUpload = pastaUpload;
	}

}