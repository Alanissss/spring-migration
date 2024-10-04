$(document).ready(function () {
    carregarProdutos();
    carregarCategorias();
    carregarUsuarios();

    function mostrarTabela(tabela) {
        $('#tabelaProdutos, #tabelaCategorias, #tabelaUsuarios').addClass('d-none'); 
        $(tabela).removeClass('d-none'); 
        $('#formProduto, #formCategoria, #formUsuario').addClass('d-none'); 
        if (tabela === '#tabelaProdutos') {
            $('#formProduto').removeClass('d-none');
        } else if (tabela === '#tabelaCategorias') {
            $('#formCategoria').removeClass('d-none');
        } else if (tabela === '#tabelaUsuarios') {
            $('#formUsuario').removeClass('d-none');
        }
    }

    function carregarProdutos() {
        $.ajax({
            url: 'http://localhost:8080/produtos',
            method: 'GET',
            success: function (produtos) {
                $('#tabelaProdutos tbody').empty();
                produtos.forEach(produto => {
                    $('#tabelaProdutos tbody').append(`
                        <tr>
                            <td>${produto.id}</td>
                            <td>${produto.nome}</td>
                            <td>${produto.valor.toFixed(2).replace('.', ',')}</td>
                            <td>
                                <button class="btn btn-warning btnEditarProduto" data-id="${produto.id}">Editar</button>
                                <button class="btn btn-danger btnExcluirProduto" data-id="${produto.id}">Excluir</button>
                            </td>
                        </tr>
                    `);
                });
            }
        });
    }

    function carregarCategorias() {
        $.ajax({
            url: 'http://localhost:8080/categorias',
            method: 'GET',
            success: function (categorias) {
                $('#tabelaCategorias tbody').empty();
                categorias.forEach(categoria => {
                    $('#tabelaCategorias tbody').append(`
                        <tr>
                            <td>${categoria.id}</td>
                            <td>${categoria.descricao}</td>
                            <td>
                                <button class="btn btn-warning btnEditarCategoria" data-id="${categoria.id}">Editar</button>
                                <button class="btn btn-danger btnExcluirCategoria" data-id="${categoria.id}">Excluir</button>
                            </td>
                        </tr>
                    `);
                });
            }
        });
    }

    function carregarUsuarios() {
        $.ajax({
            url: 'http://localhost:8080/usuarios',
            method: 'GET',
            success: function (usuarios) {
                $('#tabelaUsuarios tbody').empty();
                usuarios.forEach(usuario => {
                    $('#tabelaUsuarios tbody').append(`
                        <tr>
                            <td>${usuario.id}</td>
                            <td>${usuario.nome}</td>
                            <td>
                                <button class="btn btn-warning btnEditarUsuario" data-id="${usuario.id}">Editar</button>
                                <button class="btn btn-danger btnExcluirUsuario" data-id="${usuario.id}">Excluir</button>
                            </td>
                        </tr>
                    `);
                });
            }
        });
    }

    $('#formProduto').on('submit', function (e) {
        e.preventDefault();
        const id = $('#idProduto').val();
        const nome = $('#nomeProduto').val();
        const valor = $('#valorProduto').val();

        if (id) {
            $.ajax({
                url: `http://localhost:8080/produto`,
                method: 'PUT',
                data: { id: id, nome: nome, valor: valor },
                success: function () {
                    carregarProdutos();
                    limparFormulario('produto');
                }
            });
        } else {
            $.ajax({
                url: `http://localhost:8080/produto`,
                method: 'POST',
                data: { nome: nome, valor: valor },
                success: function () {
                    carregarProdutos();
                    limparFormulario('produto');
                }
            });
        }
    });

    $('#formCategoria').on('submit', function (e) {
        e.preventDefault();
        const id = $('#idCategoria').val();
        const descricao = $('#descricaoCategoria').val();

        if (id) {
            $.ajax({
                url: `http://localhost:8080/categoria`,
                method: 'PUT',
                data: { id: id, descricao: descricao },
                success: function () {
                    carregarCategorias();
                    limparFormulario('categoria');
                }
            });
        } else {
            $.ajax({
                url: `http://localhost:8080/categoria`,
                method: 'POST',
                data: { descricao: descricao },
                success: function () {
                    carregarCategorias();
                    limparFormulario('categoria');
                }
            });
        }
    });

    $('#formUsuario').on('submit', function (e) {
        e.preventDefault();
        const id = $('#idUsuario').val();
        const nome = $('#nomeUsuario').val();

        if (id) {
            $.ajax({
                url: `http://localhost:8080/usuario`,
                method: 'PUT',
                data: { id: id, nome: nome },
                success: function () {
                    carregarUsuarios();
                    limparFormulario('usuario');
                }
            });
        } else {
            $.ajax({
                url: `http://localhost:8080/usuario`,
                method: 'POST',
                data: { nome: nome },
                success: function () {
                    carregarUsuarios();
                    limparFormulario('usuario');
                }
            });
        }
    });

    $('#tabelaProdutos').on('click', '.btnEditarProduto', function () {
        const id = $(this).data('id');
        $.ajax({
            url: `http://localhost:8080/produto/${id}`,
            method: 'GET',
            success: function (produto) {
                $('#idProduto').val(produto.id);
                $('#nomeProduto').val(produto.nome);
                $('#valorProduto').val(produto.valor);
            }
        });
    });

    $('#tabelaProdutos').on('click', '.btnExcluirProduto', function () {
        const id = $(this).data('id');
        $.ajax({
            url: `http://localhost:8080/produtodelete/${id}`,
            method: 'DELETE',
            success: function () {
                carregarProdutos();
            }
        });
    });

    $('#tabelaCategorias').on('click', '.btnEditarCategoria', function () {
        const id = $(this).data('id');
        $.ajax({
            url: `http://localhost:8080/categoria/${id}`,
            method: 'GET',
            success: function (categoria) {
                $('#idCategoria').val(categoria.id);
                $('#descricaoCategoria').val(categoria.descricao);
            }
        });
    });

    $('#tabelaCategorias').on('click', '.btnExcluirCategoria', function () {
        const id = $(this).data('id');
        $.ajax({
            url: `http://localhost:8080/categoriadelete/${id}`,
            method: 'DELETE',
            success: function () {
                carregarCategorias();
            }
        });
    });

    $('#tabelaUsuarios').on('click', '.btnEditarUsuario', function () {
        const id = $(this).data('id');
        $.ajax({
            url: `http://localhost:8080/usuario/${id}`,
            method: 'GET',
            success: function (usuario) {
                $('#idUsuario').val(usuario.id);
                $('#nomeUsuario').val(usuario.nome);
            }
        });
    });

    $('#tabelaUsuarios').on('click', '.btnExcluirUsuario', function () {
        const id = $(this).data('id');
        $.ajax({
            url: `http://localhost:8080/usuariodelete/${id}`,
            method: 'DELETE',
            success: function () {
                carregarUsuarios();
            }
        });
    });

    function limparFormulario(tipo) {
        if (tipo === 'produto') {
            $('#idProduto').val('');
            $('#nomeProduto').val('');
            $('#valorProduto').val('');
        } else if (tipo === 'categoria') {
            $('#idCategoria').val('');
            $('#descricaoCategoria').val('');
        } else if (tipo === 'usuario') {
            $('#idUsuario').val('');
            $('#nomeUsuario').val('');
        }
    }

    $('#linkProdutos').on('click', function () {
        mostrarTabela('#tabelaProdutos');
        carregarProdutos();
    });

    $('#linkCategorias').on('click', function () {
        mostrarTabela('#tabelaCategorias');
        carregarCategorias();
    });

    $('#linkUsuarios').on('click', function () {
        mostrarTabela('#tabelaUsuarios');
        carregarUsuarios();
    });

    mostrarTabela('#tabelaProdutos');
});
