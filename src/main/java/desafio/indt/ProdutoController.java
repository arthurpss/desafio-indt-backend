package desafio.indt;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import desafio.indt.util.FileUtils;

@RestController
public class ProdutoController {

	@Autowired
	ProdutoRepository produtoRepository;

	@GetMapping("/produtos")
	public List<Produto> getProdutos() {
		return produtoRepository.findAll();
	}

	@GetMapping("/produto/{id}")
	public Optional<Produto> getProduto(@PathVariable Long id) {
		return produtoRepository.findById(id);
	}

	@GetMapping("/imagem/produto/{id}")
	public ResponseEntity<Resource> getImagem(@PathVariable Long id, @RequestHeader("fileName") String fileName,
			HttpServletRequest request) {
		String uploadDir = System.getProperty("user.dir").concat("/imagens-produtos/" + id + "/");
		Resource resource = null;

		if (fileName != null && !fileName.isEmpty()) {
			try {
				resource = FileUtils.loadFileAsResource(uploadDir, fileName);
			} catch (Exception e) {
				e.printStackTrace();
			}
			String contentType = null;
			try {
				contentType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());
			} catch (IOException ex) {
				ex.printStackTrace();
			}
			if (contentType == null) {
				contentType = "application/octet-stream";
			}

			return ResponseEntity.ok().contentType(MediaType.parseMediaType(contentType))
					.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
					.body(resource);
		} else {
			return ResponseEntity.notFound().build();
		}

	}

	@PostMapping("/produto")
	public Produto addProduto(@RequestPart("produto") Produto produto, @RequestPart("file") MultipartFile file)
			throws IOException {
		String fileName = StringUtils.cleanPath(file.getOriginalFilename());
		produto.setImagem(fileName);
		Produto produtoCriado = produtoRepository.save(produto);

		String uploadDir = System.getProperty("user.dir").concat("/imagens-produtos/" + produtoCriado.getId());
		FileUtils.saveFile(uploadDir, fileName, file);
		return produtoCriado;
	}

	@PutMapping("/produto/{id}")
	public Produto updateProduto(@PathVariable(value = "id") Long id, @RequestBody Produto produtoDetails) {
		Optional<Produto> produto = produtoRepository.findById(id);
		Produto novoProduto = produto.get();
		novoProduto.setNome(produtoDetails.getNome());
		novoProduto.setDescricao(produtoDetails.getDescricao());
		novoProduto.setImagem(produtoDetails.getImagem());
		novoProduto.setValor(produtoDetails.getValor());
		return produtoRepository.save(novoProduto);
	}

	@DeleteMapping("/produto/{id}")
	public void deleteProduto(@PathVariable(value = "id") Long id) {
		produtoRepository.deleteById(id);
	}
}
